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
    render() {
        const style = {
        };

        return (
            <div className="BlogApp">
                <Toolbar
                      colored
                      title="Blog-Pro"
                    />
                    {<BlogPosts/>}
            </div>
        );
    }
}

export default App;
