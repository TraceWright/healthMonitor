import React from 'react';
import './bloodPressure.scss';
import '../styles/formStyles.scss';
import '../styles/checkmark.scss';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { postData } from "../services/httpPost";
import { bpClassification } from "../enums/bpClassification";

interface IProps {}
interface IState {
    sysBp: number | undefined,
    diaBp: number | undefined,
    atDate: Date,
    classification: string | undefined,
    submitted: boolean,
    checkmark: boolean,
    success: boolean
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
        }
    }

    dateChangeHandler(date: any) {
        this.setState({ atDate: new Date(date) });
    }

    submit(sysBp: number | undefined, diaBp: number | undefined, atDate: Date) {
        this.setState({ submitted: true });
        postData(`${process.env.REACT_APP_API_URL}/bloodpressure`, { sysBp: sysBp, diaBp: diaBp, atDate: atDate })
        .then(response => {
            console.log(response);
            if (response.status === 400) {
                this.setState({ submitted: false });
                // validation error
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

    render() {

        return <div className="form-input">
            <input
                placeholder="Systole"
                type="number"
                id="sysBp"
                autoComplete="off"
                value={this.state.sysBp ? this.state.sysBp : ''}
                onChange={(event) => this.setState({ sysBp: parseInt(event.target.value) })}
                disabled={this.state.submitted ? true : false}
            />
            <input
                placeholder="Diastole"
                type="number"
                id="diaBp"
                autoComplete="off"
                value={this.state.diaBp ? this.state.diaBp : ''}
                onChange={(event) => this.setState({ diaBp: parseInt(event.target.value) })}
                disabled={this.state.submitted ? true : false}
            />
            <DatePicker
                selected={this.state.atDate}
                onChange={(event) => this.dateChangeHandler(event)}
                dateFormat="dd/MM/yyyy"
                disabled={this.state.submitted ? true : false}
            />
            <button
                className="submit"
                style={{display: this.state.submitted ? 'none' : 'block'}}
                onClick={() => this.submit(
                    this.state.sysBp,
                    this.state.diaBp,
                    this.state.atDate
                )}
                disabled={this.state.submitted ? true : false}
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
