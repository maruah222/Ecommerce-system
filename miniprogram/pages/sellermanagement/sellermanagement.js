// miniprogram/pages/goodmanagement/goodmanagement.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    sellers:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getsellers();
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

  },
  /* 获取需要审核的商家 */
  getsellers(){
    wx.request({
      url: 'http://47.105.66.104:8080/ecommerce/Manager/getNeedVerifyRegister?pageNum=1&pageSize=10',
      method: 'GET',
      success:(res)=>{
        console.log(res.data);
        this.setData({sellers:res.data.data.list})
      }
    })
  },
  /*审核商家 */
  verify:function(e){
    wx.request({
      url: 'http://47.105.66.104:8080/ecommerce/Manager/VerifyShopRegister',
      data:{
        username: e.currentTarget.dataset.id,
        value:e.currentTarget.dataset.val,
      },
      method: 'GET',
      success:(res)=>{
        if(res.data.code===200){
          wx.showToast({
            title: res.data.message,
            icon: "none",
          });
          this.getsellers();
        }
      }
    })
  }
})