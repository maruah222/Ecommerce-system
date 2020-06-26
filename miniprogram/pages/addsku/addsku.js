// pages/addsku/addsku.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    attribute1: [
      {
        "attrKey": "",
        "attrValue": ""
      },
      {
        "attrKey": "",
        "attrValue": ""
      },
    ],
    goodid: '',
    ShopId: '',
    goodname: '',
    goodpicture: '',
    introduction: '',
    isPackage: 0,
    frontpicture: '',
    categoryId: '',
    sku: {
      "attribute": "string",
      "goodid": "string",
      "leftNumber": 0,
      "number": 0,
      "picture": "string",
      "price": 0,
      "skuid": 0,
      "vipprice": 0
    },
    skutemp: [],
  },
  attributeIdInput: function (e) {
    var that = this;
    var temp = "attribute1[" + 0 + "].attrValue";
    this.setData({ [temp]: e.detail.value })
  },
  attributeId1Input: function (e) {
    var that = this;
    var temp = "attribute1[" + 1 + "].attrValue";
    this.setData({ [temp]: e.detail.value });
    var attr = JSON.stringify(this.data.attribute1);
    this.setData({ ['sku.attribute']: attr });
  },
  leftNumberInput: function (e) {
    this.setData({ ['sku.leftNumber']: e.detail.value });
    this.setData({ ['sku.number']: e.detail.value })
  },
  pictureInput: function (e) {
    this.setData({ ['sku.picture']: e.detail.value })
  },
  priceInput: function (e) {
    this.setData({ ['sku.price']: e.detail.value })
  },
  skuidInput: function (e) {
    this.setData({ ['sku.skuid']: e.detail.value })
  },
  vippriceInput: function (e) {
    this.setData({ ['sku.vipprice']: e.detail.value })
  },

  onClickadd: function () {
    console.log(this.data.sku);
    let self = this;
    wx.request({
      url: 'http://47.105.66.104:8080/ecommerce/Shop/AddSkuByGoodId',
      data: {
        GoodId:self.data.goodid,
        SkuId:1,
        num: self.data.sku.number,
        price:self.data.sku.price,
        vipprice:self.data.sku.vipprice,
        Left_number:self.data.sku.leftNumber,
        picture:self.data.sku.picture,
        Attribute:self.data.sku.attribute
      },
      success: function (res) {
        wx.showToast({
          title: '上架成功',
          icon: 'success',
        });
      },
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    self = this;
    wx.getStorage({
      key: 'goodid',
      success(res) {
        self.setData({ goodid: res.data });
        console.log(res.data)
        console.log(self.data.goodid)
      }
    });
    wx.getStorage({
      key: 'a1',
      success(res) {
        var temp = "attribute1[" + 0 + "].attrKey";
        self.setData({ [temp]: res.data })
        console.log(self.data.attribute1[0].attrKey)
      }
    });
    wx.getStorage({
      key: 'a2',
      success(res) {
        var temp = "attribute1[" + 1 + "].attrKey";
        self.setData({ [temp]: res.data })
        console.log(self.data.attribute1[1].attrKey)
      }
    });
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
    
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    
  }
})