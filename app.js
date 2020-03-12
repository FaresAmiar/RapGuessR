const Lyricist = require('lyricist/node6')
const express = require('express')
const app = express()


const lyricist = new Lyricist("_dEjK6ma3yzGgotgWeiFTY9fGs4VHLsNbKIpLwi4y_fgpoG3MV6an5eo2qNM4SSU")

//lyricist.song(3892314, { fetchLyrics: true }).then(song => { console.log(song.lyrics)});
//lyricist.search("91's").then(song => { lyricist.song(song[0].id, {fetchLyrics: true} ) } ).then( obj => { console.log(obj.lyrics)})

//lyricist.search("Plus Fort, Hornet La Frappe").then( obj => { return obj[0].id}).then( id => { return lyricist.song(id, { fetchLyrics : true})})
    //.then( song => { console.log(song.lyrics)})
let nomRapper = "Ninho";

const tabRappers = ['Booba','Ninho','Dinos','Damso','Vald','Sofiane','Rohff','Kaaris','Koba La D','Heuss L\'enfoiré',
    'Hornet La Frappe','Nekfeu','MHD','PNL','Niska','Maes','Lacrim','Kery James','Alkpote','Gael Faye']

lyricist.search(nomRapper).then( obj => { return obj[0].id}).then( id => { return lyricist.song(id, { fetchLyrics : true})})
    .then( song => { console.log(song.lyrics[song.lyrics])})



//lyricist.search("Hornet la frappe").then( resp => { console.log(resp)})

// 0-5 artistes préférés, Facile ou Difficile (trouve le mot ou 3 proposition de mot)

