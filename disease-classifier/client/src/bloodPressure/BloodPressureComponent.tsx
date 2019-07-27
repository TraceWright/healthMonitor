import React from 'react';
import './bloodPressure.scss';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

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

    // formatDate(date: Date) {
    //     return `${date.getFullYear().toString()}-${(date.getMonth() + 1).toString().padStart(2, "0")}-` +
    //         `${date.getDate().toString().padStart(2, "0")}`;
    // }

    postData = async (url = '', data = {}) => {
          const response = await fetch(url, {
            method: 'POST',
            mode: 'cors',
            cache: 'no-cache',
            credentials: 'same-origin',
            headers: {
                'Content-Type': 'application/json',
            },
            redirect: 'follow',
            referrer: 'no-referrer',
            body: JSON.stringify(data),
        });
        return await response.json();
    }

    dateChangeHandler(date: any) {
        this.setState({ atDate: new Date(date) });
    }

    submit(sysBp: number, diaBp: number, atDate: Date) {
        this.postData(`${process.env.REACT_APP_API_URL}/hypertension`, { sysBp: sysBp, diaBp: diaBp, atDate: atDate })
        .then(data => console.log(data)) // TODO
        .catch(error => console.error(error));
    }

    render() {

        return <div className="hypertension-form-input">
            <label className="form-lbl">Systole</label>
            <input
                type="number"
                id="sysBp"
                autoComplete="off"
                value={this.state.sysBp ? this.state.sysBp : ''}
                onChange={(event) => this.setState({ sysBp: parseInt(event.target.value) })}
            />
            <label className="form-lbl">Diastole</label>
            <input
                type="number"
                id="diaBp"
                autoComplete="off"
                value={this.state.diaBp ? this.state.diaBp : ''}
                onChange={(event) => this.setState({ diaBp: parseInt(event.target.value) })}
            />
            <label className="form-lbl">Date</label>
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