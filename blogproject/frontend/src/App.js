import React, { Component } from 'react';
import PropTypes from 'prop-types';
import {
    NavigationDrawer,
    Card,
    CardTitle,
    CardText,
    List,
    ListItem,
    Button,
    CardActions
} from 'react-md';
import './App.scss';
import BlogPost from './BlogPost.js';

import NewPostDialog from './NewPostDialog';
import NavItemLink from './NavItemLink';

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
    static propTypes = {
        location: PropTypes.object.isRequired,
    };
    state = { visible: false };

    add = () => {
        console.log('this is: add');

    }

    showDrawer = () => {
        this.setState({ visible: true });
      };

    hideDrawer = () => {
        this.setState({ visible: false });
    };

    handleVisibility = (visible) => {
        this.setState({ visible });
    };

    comment = () => {
        console.log('this is: comment');
    }

    render() {
        const style = {
        };

        const { location } = this.props;
        const { visible } = this.state;

        return (
        <div>
            <NavigationDrawer
                toolbarTitle="Blog-Pro"
                drawerTitle="Navigation"
                drawerChildren={
                    <List>
                        <ListItem
                            primaryText="Add Post"
                            onClick={this.add}
                        />
                    </List>
                }
                >
                <div>
                    <Card style={style} className="md-block-centered">
                        <CardTitle
                          title="Post title"
                          subtitle="Post subtitle"
                        />
                        <CardText>
                          <p>
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec laoreet orci
                            elit, sed eleifend nunc blandit auctor. Phasellus sodales vestibulum aliquet.
                            Cras neque leo, congue eu risus non, lobortis sagittis dui. Curabitur auctor
                            nibh at dignissim scelerisque. Duis urna risus, sodales vitae viverra vitae,
                            placerat eu nulla. Nam eget ante congue enim interdum consectetur. In pharetra
                            viverra tempor.
                          </p>
                          <p>
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec laoreet orci
                            elit, sed eleifend nunc blandit auctor. Phasellus sodales vestibulum aliquet.
                            Cras neque leo, congue eu risus non, lobortis sagittis dui. Curabitur auctor
                            nibh at dignissim scelerisque. Duis urna risus, sodales vitae viverra vitae,
                            placerat eu nulla. Nam eget ante congue enim interdum consectetur. In pharetra
                            viverra tempor. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec laoreet orci
                            elit, sed eleifend nunc blandit auctor. Phasellus sodales vestibulum aliquet.
                            Cras neque leo, congue eu risus non, lobortis sagittis dui. Curabitur auctor
                            nibh at dignissim scelerisque. Duis urna risus, sodales vitae viverra vitae,
                            placerat eu nulla. Nam eget ante congue enim interdum consectetur. In pharetra
                            viverra tempor.
                          </p>

                        </CardText>
                        <CardActions expander>
                          <Button flat onClick={this.comment}>Comment</Button>
                          <Button flat>Delete</Button>
                        </CardActions>
                      </Card>
                </div>
                <div>
                    <Card class="App-blog_card" className="md-block-centered">
                        <CardTitle
                          title="Post title"
                          subtitle="Post subtitle"
                        />
                        <CardText>
                          <p>
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec laoreet orci
                            elit, sed eleifend nunc blandit auctor. Phasellus sodales vestibulum aliquet.
                            Cras neque leo, congue eu risus non, lobortis sagittis dui. Curabitur auctor
                            nibh at dignissim scelerisque. Duis urna risus, sodales vitae viverra vitae,
                            placerat eu nulla. Nam eget ante congue enim interdum consectetur. In pharetra
                            viverra tempor.
                          </p>

                        </CardText>
                        <CardActions expander>
                          <Button flat onClick={this.comment}>Comment</Button>
                          <Button flat>Delete</Button>
                        </CardActions>
                      </Card>
                </div>
                <div>
                    <Card style={style} className="md-block-centered">
                        <CardTitle
                          title="Post title"
                          subtitle="Post subtitle"
                        />
                        <CardText>
                          <p>
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec laoreet orci
                            elit, sed eleifend nunc blandit auctor. Phasellus sodales vestibulum aliquet.
                            Cras neque leo, congue eu risus non, lobortis sagittis dui. Curabitur auctor
                            nibh at dignissim scelerisque. Duis urna risus, sodales vitae viverra vitae,
                            placerat eu nulla. Nam eget ante congue enim interdum consectetur. In pharetra
                            viverra tempor.
                          </p>
                          <p>
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec laoreet orci
                            elit, sed eleifend nunc blandit auctor. Phasellus sodales vestibulum aliquet.
                            Cras neque leo, congue eu risus non, lobortis sagittis dui. Curabitur auctor
                            nibh at dignissim scelerisque. Duis urna risus, sodales vitae viverra vitae,
                            placerat eu nulla. Nam eget ante congue enim interdum consectetur. In pharetra
                            viverra tempor. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec laoreet orci
                            elit, sed eleifend nunc blandit auctor. Phasellus sodales vestibulum aliquet.
                            Cras neque leo, congue eu risus non, lobortis sagittis dui. Curabitur auctor
                            nibh at dignissim scelerisque. Duis urna risus, sodales vitae viverra vitae,
                            placerat eu nulla. Nam eget ante congue enim interdum consectetur. In pharetra
                            viverra tempor.
                          </p>

                        </CardText>
                        <CardActions expander>
                          <Button flat onClick={this.comment}>Comment</Button>
                          <Button flat>Delete</Button>
                        </CardActions>
                      </Card>
                </div>
            </NavigationDrawer>
        </div>
        );
    }
}

export default App;
