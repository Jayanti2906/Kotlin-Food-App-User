package com.jayanti.myswayuser

class ImageUploadInfo {


    var imageName: String= ""

    var imageURL: String=""
    var menu:String = ""

    constructor() {

    }

    constructor(name: String, url: String, menu:String) {

        this.imageName = name
        this.imageURL = url
        this.menu = menu
    }

}