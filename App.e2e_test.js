Feature('Home Page')

Scenario('test something', (I) => {
    I.amOnPage('/')
    I.see('Movie Search')
    I.seeElement(".searchBox");
    I.seeElement({id: 'searchButton'});
    I.seeElement({id: 'clearButton'});
    I.seeElement({id: 'searchInput'});
    I.seeElement(".searchMovieContainer");
    I.seeElement(".savedMovieContainer");
    I.seeElement(".searchMovieElement");
})