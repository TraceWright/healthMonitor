import React from 'react';
import { BrowserRouter, Route } from "react-router-dom";
import Hypertension from './components/bloodPressure/BloodPressureComponent';
import EgfrComponent from './components/egfr/EgfrComponent';
import HistoryComponent from './components/history/HistoryComponent';
import ContactComponent from './components/contact/ContactComponent';
import MenuContainer from "./components/menu/menu";

const App: React.FC = () => {
  return (
    <BrowserRouter>
      <Route path="/" exact component={MenuContainer} />
        <Route path="/home" exact component={MenuContainer} />
        <Route path="/bloodpressure/" component={Hypertension} />
        <Route path="/egfr/" component={EgfrComponent} />
        {/* <Route path="/history/" component={HistoryComponent} /> */}
        <Route path="/contact/" component={ContactComponent} />
    </BrowserRouter>
  );
}

export default App;
