import React, { Component } from 'react';
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

class App extends Component {

    add = () => {
        console.log('this is: add');
        return (
            <div>
                <BlogPost/>
            </div>
        )
    }

    render() {
        const style = { maxWidth: 320 };
        return (
        <div>
            <NavigationDrawer
                toolbarTitle="Blog-Pro"
                drawerTitle="Navigation"
                drawerChildren={
                    <List>
                        <ListItem
                            primaryText="New Post"
                            onClick={this.add}
                        />
                    </List>
                }>
            </NavigationDrawer>
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

                    </CardText>
                    <CardActions expander>
                      <Button flat>Comment</Button>
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

                    </CardText>
                    <CardActions expander>
                      <Button flat>Comment</Button>
                    </CardActions>
                  </Card>
            </div>
        </div>
        );
    }
}

export default App;
