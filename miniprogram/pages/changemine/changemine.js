// pages/changemine/changemine.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    token:"",
    Name: "",
    password: "",
    telephone: "",
    Address: ""
  },

  PasswordInput: function(e) {
    this.setData({
      password: e.detail.value
    })
  },

  telephoneInput: function(e) {
    this.setData({
      telephone: e.detail.value
    })
  },


  AddressInput: function(e) {
    this.setData({
      Address: e.detail.value
    })
  },

  getdata:function(){
    let self=this;
    wx.request({
      url: 'http://47.105.66.104:8080/ecommerce/User/GetUserInformationByUserId',
      header: {
        'Authorization': 'Bearer ' + self.data.token
      },
      data: {
        UserId:self.data.Name
      },
      success: function (res) {
        console.log(res.data);
        self.setData({password:res.data.data.userpassword});
        self.setData({telephone:res.data.data.usertelephone});
        self.setData({Address:res.data.data.useraddress});
      }
    })
  },
  formSubmit: function() {
    let self = this;
    wx.request({
      url: 'http://47.105.66.104:8080/ecommerce/User/ModifyUserInformation',
      header: {
        'Authorization': 'Bearer ' + self.data.token
      },
      data: {
        UserId: self.data.Name,
        password: self.data.password,
        UserAddress: self.data.Address,
        telephone: self.data.telephone,
      },
      success: function(res) {
        wx.navigateBack({
          delta: 1
        });
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    let self = this;
    wx.getStorage({
        key: 'userid',
        success: function(res) {
          self.setData({
            Name: res.data
          })}
      });
    wx.getStorage({
      key: 'token',
      success(res) {
        self.setData({
          token: res.data
        }),
        self.getdata();
      }
    });
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})