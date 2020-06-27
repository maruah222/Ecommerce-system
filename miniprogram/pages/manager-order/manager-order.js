// pages/manager-order/manager-order.js
Page({

  /**
   * 页面的初始数据
   */
    data: {
      ShopId:"",
      orderID:"",
      ShopId:"",
      orders: [],
    },

  getdata(){
    let self=this;
    wx.request({
      url: 'http://47.105.66.104:8080/ecommerce/Shop/getNeedVerifyReturn',
      data:{
        ShopId:self.data.ShopId,
        pageNum:1,
        pageSize:6
      },
      success(res){
        self.setData({orders:res.data.data.list});
        console.log(self.data.orders);
      }
    })
  },  

  getorderid(e){
    let self=this;
    console.log(e);
    this.setData({ orderID:e.currentTarget.dataset.id});
    console.log(this.data.orderID);
    wx.showModal({
      title: '提示',
      content: '是否要退货',
      confirmText:"退货",
      cancelText:"拒绝",
      success(res) {
        if (res.confirm) {
          self.waitsent();
        } else if (res.cancel) {
          self.waitquit();
        }
      },
    })
  },

  waitsent(){
    let self=this;
    wx.request({
      url: 'http://47.105.66.104:8080/ecommerce/Shop/VerifyReturn',
      data:{
        OrderId:self.data.orderID,
        value:1
      },
      success(res){
        console.log(res);
        self.getdata();
      }
    })
  },

  waitquit() {
    let self = this;
    wx.request({
      url: 'http://47.105.66.104:8080/ecommerce/Shop/VerifyReturn',
      data: {
        OrderId: self.data.orderID,
        value: 2
      },
      success(res) { 
        console.log(res)
        self.getdata();
       }
    })
  },

  
  onClickadd:function(){
    let self=this;
    wx.getStorage({
      key: 'sellerid',
      success: function(res) {
        self.setData({ShopId:res.data});
        console.log(self.data.ShopId);
        wx.request({
          url: 'http://47.105.66.104:8080/ecommerce/Shop/ShowOrderExcelByShopId',
          data:{
            ShopId:self.data.ShopId
          },
          success: function(res) {console.log(res)},
        })
      },
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let self = this;
    wx.getStorage({
      key: 'sellerid',
      success: function (res) {
        self.setData({ ShopId: res.data });
        self.getdata();
      },
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