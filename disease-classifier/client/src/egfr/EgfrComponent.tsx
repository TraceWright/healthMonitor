import React from 'react';
 
class EgfrComponent extends React.Component {

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

    render() {
        this.postData(`${process.env.REACT_APP_API_URL}/egfr`, [{ egfr: 70, atDate: Date.now() }])

        return <div>Kidney Disease</div>
    }
}

export default EgfrComponent;