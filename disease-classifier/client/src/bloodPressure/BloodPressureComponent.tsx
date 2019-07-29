import React from 'react';
import '../styles/sharedStyles.scss';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { postData } from "../services/httpPost";

interface IProps {}
interface IState {
    sysBp: number | undefined,
    diaBp: number | undefined,
    atDate: Date,
    classification: string | undefined,
}
 
class Hypertension extends React.PureComponent<IProps, IState> {
    constructor(props: IProps) {
        super(props)
        
        this.state = {
            sysBp: undefined,
            diaBp: undefined,
            atDate: new Date(),
            classification: undefined,
        }
    }

    dateChangeHandler(date: any) {
        this.setState({ atDate: new Date(date) });
    }

    submit(sysBp: number, diaBp: number, atDate: Date) {
        postData(`${process.env.REACT_APP_API_URL}/bloodpressure`, { sysBp: sysBp, diaBp: diaBp, atDate: atDate })
        .then(data => console.log(data)) // TODO
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
            />
            <input
                placeholder="Diastole"
                type="number"
                id="diaBp"
                autoComplete="off"
                value={this.state.diaBp ? this.state.diaBp : ''}
                onChange={(event) => this.setState({ diaBp: parseInt(event.target.value) })}
            />
            <DatePicker
                selected={this.state.atDate}
                onChange={(event) => this.dateChangeHandler(event)}
            />
            <div></div><button
                className="submit"
                onClick={() => this.submit(
                    this.state.sysBp ? this.state.sysBp : -1,
                    this.state.diaBp ? this.state.diaBp : -1,
                    this.state.atDate
                )}
            >Submit</button>
            <div></div>
        </div>
    }
}

export default Hypertension;
