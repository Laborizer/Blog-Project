import {TextField, FontIcon, Autocomplete} from "react-md";
import React, {PureComponent} from "react";

export default class Search extends PureComponent {
    constructor(props) {
        super(props);
    }
    render() {
        let titles = this.props.data.map(item => (
            item.title
        ));

        let tags = this.props.tagData
        let hits = [];
        return (
            <div  className="md-grid">
                <Autocomplete
                    id="searchField"
                    lineDirection="center"
                    placeholder={"Search"}
                    resize={{min: 640, max: 1000}}
                    leftIcon={<FontIcon>search</FontIcon>}
                    className="md-cell md-cell--bottom "
                    data={hits}
                    filter={Autocomplete.caseInsensitiveFilter}
                    onAutocomplete={this.props.onAutocomplete}
                    onChange={this.props.onChange}
                />
            </div>
        );
    }
}