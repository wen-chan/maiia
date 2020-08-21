import React, { Component } from "react";
import "./App.css";

class App extends Component {
    constructor() {
        super();
        this.state = { data: [] };
    }

    componentDidMount() {
        fetch(`http://localhost:8080/posts`)
            .then(res => res.json())
            .then(json => this.setState({ data: json }));
    }

    render() {
        return (
            <div>
                <h1 className="title">POSTS</h1>
                {this.state.data.map(group =>
                    <div className="post" key={group.id}>
                        <div className="post-id">{group.id}</div>
                        <div className="post-title">{group.title}</div>
                        <div className="post-user">USER {group.userId}</div>
                        <div>{group.body}</div>
                    </div>
                )}
            </div>
        );
    }
}

export default App;