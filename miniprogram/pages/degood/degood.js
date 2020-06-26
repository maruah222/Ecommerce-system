// miniprogram/pages/goodmanagement/goodmanagement.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    token:null,
    up_goods:[]
  },
  degood:function(e){
    wx.request({
      url: 'http://47.105.66.104:8080/ecommerce/Manager/WithdrawGoodsByGoodId',
      data:{
        GoodId:e.currentTarget.dataset.id,
      },
      method: 'GET',
      success:(res)=>{
        console.log(res);
        wx.request({
          url: 'http://47.105.66.104:8080/ecommerce/User/GetAllGoods',
          data:{
            pageNum:1,
            pageSize:111,
          },
          method: 'GET',
          success:(res1)=>{
            this.setData({up_goods:res1.data.data.list});
            wx.showToast({
              title: res1.data.message,
              icon: "none",
            });
          }
        })
      }
    })

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
    wx.request({
      url: 'http://47.105.66.104:8080/ecommerce/User/GetAllGoods',
      data:{
        pageNum:1,
        pageSize:111,
      },
      method: 'GET',
      success:(res1)=>{
        this.setData({up_goods:res1.data.data.list});
        console.log(this.data.up_goods);
      }
    })
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