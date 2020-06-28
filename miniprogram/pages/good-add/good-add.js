// index/list.js
// pages/good-add/good-add.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    index: 0,
    attribute1: [],
    skuone: {
      "attrKey": "",
      "attrValue": ""
    },
    skusku: {},
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
  indexInput: function (e) {
    let self = this;
    this.setData({ index: e.detail.value })
    console.log(this.data.index);
    var a = this.data.attribute1;
    for (var i = 0; i < this.data.index; i++) {
      a.push(JSON.parse(JSON.stringify(self.data.skuone)));
    };
    var y = this.data.attribute1
    this.setData({ attribute1: y });
    console.log(self.data.attribute1);
  },
  GoodnameInput: function (e) {
    this.setData({ goodname: e.detail.value })
  },
  GoodpictureInput: function (e) {
    this.setData({ goodpicture: e.detail.value })
  },
  introductionInput: function (e) {
    this.setData({ introduction: e.detail.value });
  },
  isPackageInput: function (e) {
    this.setData({ isPackage: e.detail.value })
  },
  FrontpictureInput: function (e) {
    this.setData({ frontpicture: e.detail.value })
  },
  categoryIdInput: function (e) {
    this.setData({ categoryId: e.detail.value })
  },
  key1Input: function (e) {
    var x = e.currentTarget.dataset.index;
    var that = this;
    var temp = "attribute1[" + x + "].attrKey";
    this.setData({ [temp]: e.detail.value })
  },
  attributeIdInput: function (e) {
    var x = e.currentTarget.dataset.index;
    var that = this;
    var temp = "attribute1[" + x + "].attrValue";
    this.setData({ [temp]: e.detail.value });
    if (e.currentTarget.dataset.index == (self.data.attribute1.length - 1)) {
      var attr = JSON.stringify(this.data.attribute1);
      this.setData({ ['sku.attribute']: attr });
    }
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

  add: function () {
    let x = JSON.parse(JSON.stringify(this.data.sku));
    this.data.skutemp.push(x);
    console.log(this.data.skutemp);
  },

  onClickadd: function () {
    console.log(this.data.sku)
    let self = this;
    wx.request({
      url: 'http://47.105.66.104:8080/ecommerce/Shop/ApplyGoodsOn',
      data: {
        categoryId: self.data.categoryId,
        frontpicture: self.data.frontpicture,
        goodId: self.data.goodid,
        goodname: self.data.goodname,
        goodpicture: self.data.goodpicture,
        introduction: self.data.introduction,
        isPackage: self.data.isPackage,
        shopId: self.data.ShopId,
        skus: self.data.skutemp,
      },
      method: 'POST',
      dataType: 'json',
      header: { 'content-type': 'application/json' },
      success: function (res) {
        wx.showToast({
          title: '上架成功请等待审核',
          icon: 'success',
        });
      },
      fail: function (res) {
        wx.showToast({
          title: '上架失败',
          icon: 'none',
        });
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var time = parseInt(new Date().getTime() / 1000) + '';
    this.setData({ goodid: time });
    this.setData({ ['sku.goodid']: time });
    console.log(this.data.goodid)
    self = this;
    wx.getStorage({
      key: 'sellerid',
      success(res) {
        self.setData({ ShopId: res.data });
        console.log(res.data)
        console.log(self.data.ShopId)
      }
    })
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
