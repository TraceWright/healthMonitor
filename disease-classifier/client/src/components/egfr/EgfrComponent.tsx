import React from 'react';
import '../../styles/formStyles.scss';
import DatePicker from "react-datepicker";
import { postData } from "../../services/httpPost";
import { VALIDATION_MSG, inputValidations } from "../../utilities/validation";
import { egfrClassification } from "../../enums/egfrClassification";
 
interface IProps {}

interface IValidationState {
    egfr: { 
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
    egfr: { 
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
    egfr: number | undefined,
    atDate: Date,
    classification: string | undefined,
    submitted: boolean,
    checkmark: boolean,
    success: boolean,
    validation: IValidationState
}

class EgfrComponent extends React.PureComponent<IProps, IState> {
    constructor(props: IProps) {
        super(props)
        
        this.state = {
            egfr: undefined,
            atDate: new Date(),
            classification: undefined,
            submitted: false,
            checkmark: false,
            success: false,
            validation: initialValidationState
        }
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
        state.egfr && inputValidations(state.egfr.toString()) ? Function.prototype() : this.setValidation("egfr", VALIDATION_MSG, false)
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

    dateChangeHandler(date: any) {
        this.resetValidation({ ...this.state.validation, atDate: { valid: true, message: '', touched: true }});
        this.setState({ atDate: new Date(date) });
    }

    submit(egfr: number, atDate: Date, validation: IValidationState) {
        this.validateAll(this.state);
        if (this.areAllValid(validation) !== false) {
            this.setState({ submitted: true });
            postData(`${process.env.REACT_APP_API_URL}/egfr`, { egfr: egfr, atDate: atDate })
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
                } else if (response.egfr && response.egfr.egfr && response.egfr.atDate) {
                    this.setState({
                        egfr: response.egfr.egfr,
                        atDate: new Date(response.egfr.atDate),
                        classification: response.classification,
                        success: true,
                        checkmark: true,
                    });
                    this.resetValidation(initialValidationState);
                    setTimeout(() => { this.setState({ checkmark: false }) }, 1200);
                }
            })
            .catch(error => console.error(error));
        }
    }

    render() {
        return <div className="form-input">
         <input
            placeholder="eGFR"
            type="number"
            id="egfr"
            autoComplete="off"
            value={this.state.egfr ? this.state.egfr : ''}
            onChange={(event) => this.setState({ egfr: parseInt(event.target.value) })}
            disabled={this.state.submitted ? true : false}
                style={{ border: this.state.validation.egfr.valid === false && this.state.validation.egfr.touched ? '2px solid red' : ''}}
                onBlur={(e) => this.onBlurValidate(e.target.id, e.target.value)}
        />
        <label
            className="validation-error"
            style={{ visibility: this.state.validation.egfr.valid ? 'hidden' : 'visible' }}
        >{this.state.validation.egfr.message}</label>
        <DatePicker
            selected={this.state.atDate}
            onChange={(event) => this.dateChangeHandler(event)}
        />
        <label
            className="validation-error"
            style={{ visibility: this.state.validation.atDate.valid ? 'hidden' : 'visible' }}
        >{this.state.validation.atDate.message}</label>
        <div></div><button
            className="submit"
            style={{display: this.state.success ? 'none' : 'block'}}
            onClick={() => this.submit(
                this.state.egfr ? this.state.egfr : -1,
                this.state.atDate,
                this.state.validation
            )}
            disabled={ this.state.submitted ? true : false }
        >Submit</button>
         <button
                className="reset"
                style={{display: this.state.success ? 'block' : 'none'}}
                onClick={() => this.setState({ 
                    submitted: false,
                    egfr: undefined,
                    atDate: new Date(),
                    success: false,
                })}
            >Reset</button>
        <div className="checkbox-wrapper">
                <div style={{ display: this.state.checkmark ? 'block' : 'none' }} className="check-wrap"></div>
            </div>
            <div className="results">
                <label className={ this.state.success && this.state.checkmark === false ? 'enter-active' : 'enter' }>
                    {`Submitted eGFR: ${this.state.egfr} mL/min/1.73m^2`}
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
                        egfrClassification[parseInt(this.state.classification)] :
                        ''
                    }`}
                </label>
            </div>
    </div>
    }
}

export default EgfrComponent;