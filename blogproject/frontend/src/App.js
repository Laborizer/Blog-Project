import React, { Component } from 'react';
import PropTypes from 'prop-types';
import {
    Button,
    Toolbar
} from 'react-md';
import './App.scss';
import BlogPosts from './BlogPosts.js';

import NewPostDialog from './NewPostDialog';

const styles = {
  content: { minHeight: 'auto' },
};

const TO_PREFIX = '/blog';

const navItems = [{
    label: 'NewPostDialog',
    to: `${TO_PREFIX}/new`,
    exact: true,
    //icon: 'inbox',
}];

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
                <Toolbar className="md-toolbar-relative"
                      colored
                      title="Blog-Pro"
                    />
                <Button className="addPost"
                    style={postButtonStyle}
                    raised
                    secondary swapTheming
                    onClick={this.addPost}>Make a new Post</Button>
                {<BlogPosts/>}
            </div>
        );
    }
}

export default App;
