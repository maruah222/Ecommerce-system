Page({

  /**
   * 页面的初始数据
   */
  data: {
    sellernum:123,
    skutemp:{
      "usename":1,
      "password":1
    },
    sku:[],
    attrValueList: [
      {
        "attrKey": "型号",
        "attrValue": "2"
      },
      {
        "attrKey": "颜色",
        "attrValue": "白色"
      },
    ]
  },

  AccountInput: function (e) {
    this.setData({ ['skutemp.usename']: e.detail.value })
  },
  PasswordInput: function (e) {
    this.setData({ ['skutemp.password']: e.detail.value })
  },

  login:function(){
    console.log(this.data.attrValueList[1].attrValue);
    console.log(this.data.skutemp)
    let x = JSON.parse(JSON.stringify(this.data.skutemp));
    this.data.sku.push(x);
    console.log(this.data.sku);
    var citystr = JSON.stringify(this.data.sku);
    console.log(citystr);
    var weatherObj = JSON.parse(citystr);
    console.log(weatherObj);
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
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