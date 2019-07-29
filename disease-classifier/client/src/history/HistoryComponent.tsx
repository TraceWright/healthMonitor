import React from 'react';
import '../styles/formStyles.scss';
import "react-datepicker/dist/react-datepicker.css";

interface IProps {}
interface IState {
 
}
 
class HistoryComponent extends React.PureComponent<IProps, IState> {
    constructor(props: IProps) {
        super(props)
        
        this.state = {
            
        }
    }

    render() {

        fetch(`${process.env.REACT_APP_API_URL}/egfr-history`)
        .then(data => console.log(data)) // TODO
        .catch(error => console.error(error));

        return <div >
        </div>
    }
}

export default HistoryComponent;