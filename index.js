import './bootstrap/dist/css/bootstrap.css';
import './bootstrap/dist/css/bootstrap-theme.css';
// import './font-awesome/css/font-awesome.css';
import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './component/App';
import registerServiceWorker from './registerServiceWorker';

ReactDOM.render(<App />, document.getElementById('root'));
registerServiceWorker();
