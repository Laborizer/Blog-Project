import React, { Component } from 'react';
import PropTypes from 'prop-types';
import {
    Button,
    Toolbar
} from 'react-md';
import './App.scss';
import BlogPostView from './BlogPostView.jsx';

import NewPostDialog from './NewPostDialog.jsx';
import Search from './Search.jsx';

const styles = {
  content: { minHeight: 'auto' },
};

class App extends Component {

    addPost = () => {
        console.log("addPost");
    }

    render() {
        const style = {
        };

        const postButtonStyle = {
            margin: 10,
            minWidth: 320,
            float: 'middle'
        };

        return (
            <div className="BlogApp">
                <Toolbar
                      colored
                      title="Blog-Pro"
                      children={<Search/>}
                    />
                {<BlogPostView/>}
            </div>
        );
    }
}

export default App;
