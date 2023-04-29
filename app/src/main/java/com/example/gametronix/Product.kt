package com.example.gametronix

class Product {
    var productCategory: String? = null
    var productTitle: String? = null
    var productName: String? = null
    var productPrice: String? = null
    var productDescription: String? = null
    var productImage: Int = 0

    constructor(){ }

    constructor(productCategory: String, productTitle: String, productName: String,
                productPrice: String, productDescription: String, productImage: Int) {
        this.productCategory = productCategory
        this.productTitle = productTitle
        this.productName = productName
        this.productPrice = productPrice
        this.productDescription = productDescription
        this.productImage = productImage
    }
}