// pages/good-change/good-change.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    skuid: "",
    goodname: "",
    goodid: "",
    gooddata: [],
    good: {
      "skuid": 0,
      "price": 0,
      "picture": "",
      "attibute": [],
      "allsell":0,
      "LeftNumber":0,
    },
    goodtemp: [],
  },

  getdata: function () {
    let self = this;
    wx.request({
      url: 'http://47.105.66.104:8080/ecommerce/Shop/getSkuByGoodId',
      data: {
        GoodId: self.data.goodid,
        pageNum: 1,
        pageSize: 10,
      },
      method: 'GET',
      success(res) {
        console.log(res.data);
        for (var i = 0; i < res.data.data.list.length; i++) {
          self.setData({ ['good.skuid']: res.data.data.list[i].skuid });
          self.setData({ ['good.price']: res.data.data.list[i].price });
          self.setData({ ['good.picture']: res.data.data.list[i].picture });
          self.setData({ ['good.LeftNumber']: res.data.data.list[i].leftNumber });
          self.setData({ ['good.allsell']: res.data.data.list[i].number - res.data.data.list[i].leftNumber});
          self.setData({ ['good.attribute']: JSON.parse(res.data.data.list[i].attribute) });
          var y = JSON.parse(JSON.stringify(self.data.good));
          var z = self.data.goodtemp;
          z.push(y);
          var a = JSON.parse(JSON.stringify(z))
          self.setData({ gooddata: a });
        }
        var b = [];
        self.setData({ goodtemp: b });
        console.log(self.data.gooddata);
        console.log(self.data.goodtemp);
      }
    })
  },

  addstock:function(){
    wx.navigateTo({
      url: '/pages/addstock/addstock',
    })
  },

  changeprice:function(){
    wx.navigateTo({
      url: '/pages/changesku/changesku',
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let self = this;
    wx.getStorage({
      key: 'goodid',
      success: function (res) {
        self.setData({ goodid: res.data });
        self.getdata();
      },
    })
    wx.getStorage({
      key: 'goodname',
      success: function (res) {
        self.setData({ goodname: res.data });
        console.log(self.data.goodname);
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