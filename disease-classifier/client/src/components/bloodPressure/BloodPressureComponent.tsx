import React from 'react';
import './bloodPressure.scss';
import '../../styles/formStyles.scss';
import '../../styles/checkmark.scss';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { postData } from "../../services/httpPost";
import { bpClassification } from "../../enums/bpClassification";
import { VALIDATION_MSG, inputValidations } from "../../utilities/validation";

interface IProps {}

interface IValidationState {
    sysBp: { 
        valid: boolean,
        message: string,
        touched: boolean,
    },
    diaBp: { 
        valid:  boolean,
        message: string,
        touched: boolean,
    },
    atDate: { 
        valid: boolean,
        message: string,
        touched: boolean,
    },
    [index: string]: {
        valid: boolean,
        message: string,
        touched: boolean,
    }
}

const initialValidationState: IValidationState = {
    sysBp: { 
        valid: false,
        message: '',
        touched: false,
    },
    diaBp: { 
        valid: false,
        message: '',
        touched: false,
    },
    atDate: { 
        valid: true,
        message: '',
        touched: false,
    },
}

interface IState {
    sysBp: number | undefined,
    diaBp: number | undefined,
    atDate: Date,
    classification: string | undefined,
    submitted: boolean,
    checkmark: boolean,
    success: boolean,
    validation: IValidationState,
}
 
class Hypertension extends React.PureComponent<IProps, IState> {
    constructor(props: IProps) {
        super(props)
        
        this.state = {
            sysBp: undefined,
            diaBp: undefined,
            atDate: new Date(),
            classification: undefined,
            submitted: false,
            checkmark: false,
            success: false,
            validation: initialValidationState
        }
    }
    
    private dateChangeHandler(date: any): void {
        this.setState({ atDate: new Date(date) });
    }
    
    private setValidation(field: keyof IValidationState, message: string, valid: boolean): void {
        this.setState((prevState: IState) => {
            return ({
                validation: {
                    ...prevState.validation,
                    [field]: {
                        ...prevState.validation[field],
                        valid,
                        message,
                        touched: true
                    }
                }                                    
            });
        });
    }

    private onBlurValidate(field: keyof IValidationState, value: string): void {
        inputValidations(value) ?
            this.resetValidation({ ...this.state.validation, [field]: { valid: true, message: '', touched: true }}) :
            this.setValidation(field, VALIDATION_MSG, false)
    }

    private validateAll(state: IState): void {
        state.sysBp && inputValidations(state.sysBp.toString()) ? Function.prototype() : this.setValidation("sysBp", VALIDATION_MSG, false)
        state.diaBp && inputValidations(state.diaBp.toString()) ? Function.prototype() : this.setValidation("diaBp", VALIDATION_MSG, false)
    }

    private resetValidation(validationState: IValidationState): void {
        this.setState({ validation: validationState });
    }

    private areAllValid(validationState: IValidationState): boolean {
        for (const key in validationState) {
            if (validationState[key].valid === false) {
                return false;
            }
        }
        return true;
    }

    private submit(sysBp: number | undefined, diaBp: number | undefined, atDate: Date, validation: IValidationState): void {
        this.validateAll(this.state);
        if (this.areAllValid(validation) !== false) {
            this.setState({ submitted: true });
            this.resetValidation(initialValidationState);
            postData(`${process.env.REACT_APP_API_URL}/bloodpressure`, { sysBp: sysBp, diaBp: diaBp, atDate: atDate })
            .then(response => {
                // console.log(response);
                if (response.status === 400) {
                    if (response.errors.length > 0) {
                        this.setState({ submitted: false });
                        try {
                            response.errors.map((err: { field: keyof IValidationState, defaultMessage: string}) => {
                                this.setValidation(err.field, err.defaultMessage, false);
                            });
                        } catch {
                            console.error(response.error);
                        }
                    }
                } else if (response.sysBp && response.diaBp && response.atDate) {
                    this.setState({
                        sysBp: response.sysBp,
                        diaBp: response.diaBp,
                        atDate: new Date(response.atDate),
                        classification: response.classification,
                        success: true,
                        checkmark: true,
                    });
                    setTimeout(() => { this.setState({ checkmark: false }) }, 1200);
                }
            })
            .catch(error => console.error(error));
        }
    }

    private handleChange = (field: string, value: number): void => {
        this.setState((prevState) => ({
            ...prevState,
            [field]: value
        }));
    }

    render() {
        return <div className="form-input">
            <input
                placeholder="Systole"
                type="number"
                id="sysBp"
                autoComplete="off"
                value={this.state.sysBp ? this.state.sysBp : ''}
                onChange={(e) => this.handleChange(e.target.id, parseInt(e.target.value))}
                disabled={this.state.submitted ? true : false}
                style={{ border: this.state.validation.sysBp.valid === false && this.state.validation.sysBp.touched ? '2px solid red' : ''}}
                onBlur={(e) => this.onBlurValidate(e.target.id, e.target.value)}
            />
            <label
                className="validation-error"
                style={{ visibility: this.state.validation.sysBp.valid ? 'hidden' : 'visible' }}
            >{this.state.validation.sysBp.message}</label>
            <input
                placeholder="Diastole"
                type="number"
                id="diaBp"
                autoComplete="off"
                value={this.state.diaBp ? this.state.diaBp : ''}
                onChange={(e) => this.handleChange(e.target.id, parseInt(e.target.value))}
                disabled={this.state.submitted ? true : false}
                style={{ border: this.state.validation.diaBp.valid === false && this.state.validation.diaBp.touched ? '2px solid red' : ''}}
                onBlur={(e) => this.onBlurValidate(e.target.id, e.target.value)}
            />
            <label
                className="validation-error"
                style={{ visibility: this.state.validation.diaBp.valid ? 'hidden' : 'visible' }}
            >{this.state.validation.diaBp.message}</label>
            <DatePicker
                selected={this.state.atDate}
                onChange={(e) => this.dateChangeHandler(e)}
                dateFormat="dd/MM/yyyy"
                disabled={this.state.submitted ? true : false}
            />
            <button
                className="submit"
                style={{display: this.state.submitted ? 'none' : 'block'}}
                onClick={() => this.submit(
                    this.state.sysBp,
                    this.state.diaBp,
                    this.state.atDate,
                    this.state.validation,
                )}
                disabled={ this.state.submitted ? true : false }
            >Submit</button>
            <button
                className="reset"
                style={{display: this.state.submitted ? 'block' : 'none'}}
                onClick={() => this.setState({ 
                    submitted: false,
                    sysBp: undefined,
                    diaBp: undefined,
                    atDate: new Date(),
                    success: false,
                })}
            >Reset</button>
            <div className="checkbox-wrapper">
                <div style={{ display: this.state.checkmark ? 'block' : 'none' }} className="check-wrap"></div>
            </div>
            <div className="results">
                <label className={ this.state.success && this.state.checkmark === false ? 'enter-active' : 'enter' }>
                    {`Submitted blood pressure: ${this.state.sysBp}/${this.state.diaBp} mmHg`}
                </label>
            </div>
            <div className="results">
                <label className={ this.state.success && this.state.checkmark === false ? 'enter-active' : 'enter' }>
                    {`for date: ${this.state.atDate.toDateString()}`}
                </label>
            </div>
            <div className="results">
                <label className={ this.state.success && this.state.checkmark === false ? 'enter-active' : 'enter' }>
                    {`Classification: ${
                        typeof this.state.classification  === 'number' ?
                        bpClassification[parseInt(this.state.classification)] :
                        ''
                    }`}
                </label>
            </div>
        </div>
    }
}

export default Hypertension;
