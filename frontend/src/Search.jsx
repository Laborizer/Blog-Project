import {FontIcon, Autocomplete} from "react-md";
import React, {PureComponent} from "react";

export default class Search extends PureComponent {
    render() {
        let titles = this.props.data.map(item => item.title);

        let tags = this.props.tagData.map(item => item.tagName);

        let hits = titles.concat(tags);
        let uniqueHits = hits.filter((v, i, a) => a.indexOf(v) === i);
        return (
            <div  className="md-grid">
                <Autocomplete
                    id="searchField"
                    lineDirection="center"
                    placeholder={"Search"}
                    resize={{min: 400, max: 1000}}
                    leftIcon={<FontIcon>search</FontIcon>}
                    className="md-cell md-cell--bottom "
                    data={uniqueHits}
                    filter={Autocomplete.caseInsensitiveFilter}
                    onAutocomplete={this.props.onAutocomplete}
                    onChange={this.props.onChange}
                />
            </div>
        );
    }
}