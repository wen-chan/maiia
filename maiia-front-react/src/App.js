import React, { Component } from "react";

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
                <ul>
                    {this.state.data.map(el => (
                        <li>
                            {el.id}: {el.title}
                        </li>
                    ))}
                </ul>
            </div>
        );
    }
}

export default App;