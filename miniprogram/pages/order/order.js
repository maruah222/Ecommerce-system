// miniprogram/pages/order/order.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    token:null,
    orders:[],
    hiddenmodalput:true,
    reas:"",
    id:null,
  },
  detailorder:function(e){
    console.log(e.currentTarget.dataset.id);
  },
  reason:function(e){
    this.setData({ reas: e.detail.value });
    console.log(this.data.reas);
  },
  cancel:function(){
    this.setData({reas:""});
    this.setData({id:null});
    this.setData({hiddenmodalput:true});
  },
  confirm:function(){
    wx.request({
      url: 'http://47.105.66.104:8080/ecommerce/User/GoodsReturnApply',
      data:{
        orderId:this.data.id,
        message:this.data.reas,
      },
      method:'GET',
      header: {
        'Authorization': 'Bearer '+ this.data.token
      },
      success:(res)=>{
        wx.request({
          url: 'http://47.105.66.104:8080/ecommerce/User/GetAllOrderByUserId',
          data:{
            pageNum:1,
            pageSize:11,
          },
          method:'GET',
          header: {
            'Authorization': 'Bearer '+ this.data.token
          },
          success:(res1)=>{
           this.setData({orders:res1.data.data.list});
           this.setData({hiddenmodalput:true});
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
    wx.getStorage({
      key: 'token',
      success:(res)=>{
        this.setData({token:res.data});
        wx.request({
          url: 'http://47.105.66.104:8080/ecommerce/User/GetAllOrderByUserId',
          data:{
            pageNum:1,
            pageSize:11,
          },
          method:'GET',
          header: {
            'Authorization': 'Bearer '+ res.data
          },
          success:(res1)=>{
           this.setData({orders:res1.data.data.list});
           console.log(this.data.orders);
          }
        })
      }
    })
  },
  modalinput: function (e) {
    this.setData({
      hiddenmodalput: !this.data.hiddenmodalput
    })
    this.setData({id:e.currentTarget.dataset.id});
  },
  getgoods:function(e){
    wx.request({
      url: 'http://47.105.66.104:8080/ecommerce/User/GetGoodsSuccess',
      data:{
        orderId:e.currentTarget.dataset.id
      },
      method:'GET',
      header: {
        'Authorization': 'Bearer '+this.data.token
      },
      success:(res)=>{
        console.log(res);
        wx.request({
          url: 'http://47.105.66.104:8080/ecommerce/User/GetAllOrderByUserId',
          data:{
            pageNum:1,
            pageSize:11,
          },
          method:'GET',
          header: {
            'Authorization': 'Bearer '+ this.data.token
          },
          success:(res1)=>{
           this.setData({orders:res1.data.data.list});
          }
        })
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