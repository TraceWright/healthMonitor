import React from 'react';
import '../../styles/formStyles.scss';
import DatePicker from "react-datepicker";
import { postData } from "../../services/httpPost";
 
interface IProps {}
interface IState {
    egfr: number | undefined,
    atDate: Date,
    classification: string | undefined,
}

class EgfrComponent extends React.PureComponent<IProps, IState> {
    constructor(props: IProps) {
        super(props)
        
        this.state = {
            egfr: undefined,
            atDate: new Date(),
            classification: undefined,
        }
    }

    dateChangeHandler(date: any) {
        this.setState({ atDate: new Date(date) });
    }

    submit(egfr: number, atDate: Date) {
        postData(`${process.env.REACT_APP_API_URL}/egfr`, { egfr: egfr, atDate: atDate })
        .then(data => console.log(data)) // TODO
        .catch(error => console.error(error));
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
        />
        <DatePicker
            selected={this.state.atDate}
            onChange={(event) => this.dateChangeHandler(event)}
        />
        
        <div></div><button
            className="submit"
            onClick={() => this.submit(
                this.state.egfr ? this.state.egfr : -1,
                this.state.atDate
            )}
        >Submit</button>
        <div></div>
    </div>
    }
}

export default EgfrComponent;