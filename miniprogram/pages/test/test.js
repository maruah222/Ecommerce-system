Page({

  /**
   * 页面的初始数据
   */
  data: {
    sellernum: 123,
    token: "",
    goodid: "",
    skutemp: {
      "usename": 1,
      "password": 1
    },
    attrValueList: [{
        "attrKey": "型号",
        "attrValue": "2"
      },
      {
        "attrKey": "颜色",
        "attrValue": "白色"
      },
    ],
    GoodId: "1592550242",
    number: 1,
    price: 200,
    Attribute: '[{"attrKey":"颜色","attrValue":"黑色"},{"attrKey":"内存","attrValue":"256G"}]',

    "skus": [{
      "skuid": 7,
      "goodid": "1592550242",
      "number": 10,
      "price": 2000,
      "vipprice": 1700,
      "leftNumber": 0,
      "picture": "//img14.360buyimg.com/cms/jfs/t1/120813/1/1474/254048/5ebcaee4E7b4ecc48/0b3492048eb289e1.jpg",
      "attribute": "[{\"attrKey\":\"颜色\",\"attrValue\":\"白色\"},{\"attrKey\":\"内存\",\"attrValue\":\"128G\"}]"
    }, 
    {
      "skuid": 7,
      "goodid": "1592550242",
      "number": 10,
      "price": 2000,
      "vipprice": 1700,
      "leftNumber": 0,
      "picture": "//img14.360buyimg.com/cms/jfs/t1/120813/1/1474/254048/5ebcaee4E7b4ecc48/0b3492048eb289e1.jpg",
      "attribute": "[{\"attrKey\":\"颜色\",\"attrValue\":\"黄色\"},{\"attrKey\":\"内存\",\"attrValue\":\"64G\"}]"
    }],
    sku:{
      "skuid": 0,
      "goodid": "",
      "number": 0,
      "price": 0,
      "vipprice": 0,
      "leftNumber": 0,
      "picture": "0",
      "Attribute": []
    },
    skuss:[],
  },

  AccountInput: function(e) {
    this.setData({
      ['skutemp.usename']: e.detail.value
    })
  },
  PasswordInput: function(e) {
    this.setData({
      ['skutemp.password']: e.detail.value
    })
  },

  login: function() {
    let self = this;
    this.setData({
      goodid: parseInt(new Date().getTime() / 1000) + ''
    });
    let x = JSON.parse(this.data.skus[0].attribute);
    this.setData({sku:x})
    console.log(this.data.sku);
    for(var i=0;i<self.data.skus.length;i++)
    {
      this.setData({ ['sku.skuid']: self.data.skus[i].skuid});
      this.setData({ ['sku.goodid']: self.data.skus[i].goodid });
      this.setData({ ['sku.number']: self.data.skus[i].number });
      this.setData({ ['sku.price']: self.data.skus[i].price });
      this.setData({ ['sku.vipprice']: self.data.skus[i].vipprice });
      this.setData({ ['sku.leftNumber']: self.data.skus[i].leftNumber });
      this.setData({ ['sku.picture']: self.data.skus[i].picture });
      this.setData({ ['sku.Attribute']: JSON.parse(self.data.skus[i].attribute) });
      var y = JSON.parse(JSON.stringify(self.data.sku));
      var z=self.data.skuss;
      z.push(y);
      console.log(self.data.skuss);
      this.setData({ skuss:z});
    }
    wx.request({
      url: 'http://47.105.66.104:8080/ecommerce/User/GetGoodsDetailsByGoodId?GoodId=1592550242',
      method:'GET',
      success(res){
        for (var i = 0; i < res.data.data.skus.length; i++) {
          self.setData({ ['sku.skuid']: res.data.data.skus[i].skuid });
          self.setData({ ['sku.goodid']: res.data.data.skus[i].goodid });
          self.setData({ ['sku.number']: res.data.data.skus[i].number });
          self.setData({ ['sku.price']: res.data.data.skus[i].price });
          self.setData({ ['sku.vipprice']: res.data.data.skus[i].vipprice });
          self.setData({ ['sku.leftNumber']: res.data.data.skus[i].leftNumber });
          self.setData({ ['sku.picture']: res.data.data.skus[i].picture });
          self.setData({ ['sku.Attribute']: JSON.parse(res.data.data.skus[i].attribute) });
          var y = JSON.parse(JSON.stringify(self.data.sku));
          var z = self.data.skuss;
          z.push(y);
          console.log(self.data.skuss);
          self.setData({ skuss: z });
        }
      }
    })
    /*var citystr = JSON.stringify(this.data.sku);
    console.log(citystr);
    var weatherObj = JSON.parse(citystr);
    console.log(weatherObj);*/
    /*wx.request({
      url: 'http://47.105.66.104:8080/ecommerce/User/AddChart',  // 仅为示例，并非真实的接口地址
      header: {
        'Authorization': 'Bearer ' + self.data.token
      },
      data:{
        GoodId:this.data.GoodId,
        number:this.data.number,
        price:this.data.price,
        Attribute:this.data.Attribute
      },
      success(res) {
        console.log(res)
      },
      fail(error) { }
    })*/
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    let self = this;
    wx.getStorage({
      key: 'token',
      success: function(res) {
        self.setData({
          token: res.data
        })
      },
    })
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