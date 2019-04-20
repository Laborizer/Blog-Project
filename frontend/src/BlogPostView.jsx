import React, {PureComponent} from "react";
import BlogPostTest from './BlogPostTest.jsx';
import NewPostDialog from './NewPostDialog.jsx';

export default class BlogPostView extends PureComponent  {
    constructor(props) {
        super(props)

        this.state = {
            data: [],
            commentData: [],
            loadingData: false
        }
    }

    componentDidMount() {
        this.setState({loadingData: true});

        fetch('/getBlogItems')
            .then(response => response.json())
            .then(json => this.setState({data: json}))
        setTimeout(function(){
            fetch('/getAllComments')
                .then(response => response.json())
                .then(json => this.setState({commentData: json, loadingData: false}))
        }, 250);

/*
        fetch('/getBlogItems')
        .then(function(response) {
          return response.json()
        })
        .then(responseData => {
          this.setState({data: responseData})
          return fetch('/getAllComments')
        })
        .then(function(response) {
          return response.json();
        })
        .then(responseData => {
          this.setState({commentData: responseData, loadingData: false})
        })
        .catch(function(error) {
          console.log('Requestfailed', error)
        }); */
    }

    updateData = (newData) => {
        this.setState({loadingData: true});
        this.setState({data: newData, loadingData: false})
    }

    updateCommentData = (newData) => {
        this.setState({commentData: newData})
    }

    showData = () => {
        return (
            this.state.data.map((item) =>
                <div key={item.id}>
                    <BlogPostTest
                        id={item.id}
                        title={item.title}
                        author={item.author}
                        content={item.content}
                        creationDate={item.creationDate}
                        data={this.state.data}
                        commentData={this.state.commentData}
                        updateData={this.updateData}
                        updateCommentData={this.updateCommentData}
                    />
                </div>
            )
        );
    }

    render() {
        if(this.state.loadingData) {
            return (
                <div className="md-grid">
                    <p>Loading...</p>
                </div>
            )
        }

        if(!this.state.loadingData) {
            return (
                <div>
                    <NewPostDialog
                        data={this.state.data}
                        updateData={this.updateData}
                    />
                    {this.showData()}
                </div>
            );
        }
    }
}