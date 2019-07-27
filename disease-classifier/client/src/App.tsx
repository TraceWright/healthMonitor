import React from 'react';
import { BrowserRouter, Route } from "react-router-dom";
import Hypertension from './bloodPressure/BloodPressureComponent';
import EgfrComponent from './egfr/EgfrComponent';
import MenuContainer from "./routing/menu";

const App: React.FC = () => {
  return (
    <BrowserRouter>
      <Route path="/" exact component={MenuContainer} />
        <Route path="/home" exact component={MenuContainer} />
        <Route path="/bloodpressure/" component={Hypertension} />
        <Route path="/egfr/" component={EgfrComponent} />
    </BrowserRouter>
  );
}

export default App;
