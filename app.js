const Lyricist = require('lyricist/node6')

const lyricist = new Lyricist("_dEjK6ma3yzGgotgWeiFTY9fGs4VHLsNbKIpLwi4y_fgpoG3MV6an5eo2qNM4SSU")

//lyricist.song(3892314, { fetchLyrics: true }).then(song => { console.log(song.lyrics)});
//lyricist.search("91's").then(song => { lyricist.song(song[0].id, {fetchLyrics: true} ) } ).then( obj => { console.log(obj.lyrics)})

lyricist.search("Plus Fort, Hornet La Frappe").then( obj => { return obj[0].id}).then( id => { return lyricist.song(id, { fetchLyrics : true})})
    .then( song => { console.log(song.lyrics)})

lyricist.search("Mama no cry").then( obj => { return obj[0].id}).then( id => { return lyricist.song(id, { fetchLyrics : true})})
    .then( song => { console.log(song.lyrics)})