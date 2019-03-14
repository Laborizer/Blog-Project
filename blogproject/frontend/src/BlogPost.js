import React, { Component } from 'react';
import {
    Card,
    CardTitle,
    CardText,
    Button,
    CardActions
} from 'react-md';
import './App.scss';

class BlogPost extends Component {
    render() {
        //const {title} = this.props
        return (
            <Card className="md-block-centered">
                <CardTitle
                  title='New Post'
                  subtitle="Post subtitle"
                />
                <CardText>
                  <p>
                    A new blog post.
                  </p>

                </CardText>
                <CardActions expander>
                  <Button flat>Comment</Button>
                </CardActions>
            </Card>
        )
    }
}

export default BlogPost