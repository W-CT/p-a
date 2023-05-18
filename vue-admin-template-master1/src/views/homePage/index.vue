<template>
  <div class="hpTop">
    <el-form v-if="$store.getters.role == ''" ref="userForm" :model="userForm" label-width="50px" inline="true" style="border-bottom: solid #1482f0 1px">
      <el-form-item label="账号" style="margin-left: 30%;">
        <el-input v-model="userForm.username"/>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="userForm.password"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="login()">登录</el-button>
      </el-form-item>
    </el-form>

    <div class="right-menu" v-if="$store.getters.role != ''">
      <el-dropdown class="avatar-container" trigger="click">
        <div class="avatar-wrapper">
          <img :src="avatar+'?imageView2/1/w/80/h/80'" class="user-avatar">
          <i class="el-icon-caret-bottom" />
        </div>
        <el-dropdown-menu slot="dropdown" class="user-dropdown">
          <el-dropdown-item divided @click.native="logout">
            <span style="display:block;">登出</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>

    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
    <el-menu-item index="1">导航1</el-menu-item>
    <el-menu-item index="2">导航2</el-menu-item>
    <el-menu-item index="3">导航3</el-menu-item>
    <el-menu-item index="4">导航4</el-menu-item>
    </el-menu>

    <div class="bookList">
<!--      <div class="img" v-for="item in imageSrc.slice(index2,index2+1)" :key="item">-->
<!--        <el-image :src="item.img" style="width: 100px;height: 100px"></el-image>-->
<!--        <dl v-for="(item,index) in list.slice(index2,index2+1)" :key="index">-->
<!--          <dt>{{ item.name }}</dt>-->
<!--        </dl>-->
<!--      </div>-->
      <ul v-for="(item1,index2) in list.length" :key="index2">
        <div class="b"style="display: flex">
          <div v-for="item in imageSrc.slice(index2,index2+1)" :key="item">
            <el-image :src="item.img" style="width: 124px;height: 154px"></el-image>
          </div>
          <div class="a" style="border: solid 1px">
            <dl v-for="(item,index) in list.slice(index2,index2+1)" :key="index">
              <dt><a>{{ item.name }}</a></dt>
              <dd>{{ item.describes }}</dd>
            </dl>
          </div>
        </div>



      </ul>

    </div>
  </div>
</template>

<script>
  import store from '@/store/index.js'
  import { mapGetters } from 'vuex'
export default {
  name: '',
  store,
  data() {
    return {
      activeIndex: '1',
      userForm: {
        username: '',
        password: ''
      },
      total: 0,
      list: null,
      query:{
        page:1,
        limit:5
      },
      imageSrc: [{img: require('@/assets/homePage/1.jpeg')},
        {img: require('@/assets/homePage/2.jpeg')},
        {img: require('@/assets/homePage/3.jpeg')},
        {img: require('@/assets/homePage/4.jpg')},
        {img: require('@/assets/homePage/5.jpg')}]
    }
  },
  created() {
    this.getList()
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'avatar'
    ])
  },
  methods: {
    login() {

    },
    async logout() {
      await this.$store.dispatch('user/logout')
      this.$router.push(`/login?redirect=${this.$route.fullPath}`)
    },
    handleSelect() {

    },
    getList() {
      var vm = this
      this.axios({
        method: 'GET',
        url: 'http://localhost:8888/directory/pageinfo?pageNum='+vm.query.page+'&pageSize='+vm.query.limit,
      }).then(function(resp) {
        console.log(resp.data)
        vm.total = resp.data.total
        vm.list = resp.data.list
      })
    }
  }
}
</script>

<style lang="scss">
.hpTop {
  width: 100%;
  border: 1px solid;
  .el-input{
    width: 150px;
    height: 5px;
  }
  .el-menu {
    width: 800px;
    border: solid 1px;
    margin-left: 20%;
  }
  .el-menu-item {
    margin-left: 70px;
    margin-right: 50px;
  }
  .bookList {
    float: left;
    width: 800px;
    border: solid 1px;
    margin-left: 20%;
  }
}
ul {
  float: left;
}
dt {
  border-bottom: #20a0ff solid 1px;
}
dl {
  padding: 0 0 0 10px ;
}
dd {
  width: 170px;
  line-height: 20px;
  padding: 7px 0 0;
  text-indent: 2em;
  margin-inline-start: 0px;
}
.right-menu {
  float: right;
  height: 100%;
  line-height: 50px;

  &:focus {
    outline: none;
  }

  .right-menu-item {
    display: inline-block;
    padding: 0 8px;
    height: 100%;
    font-size: 18px;
    color: #5a5e66;
    vertical-align: text-bottom;

    &.hover-effect {
      cursor: pointer;
      transition: background .3s;

      &:hover {
        background: rgba(0, 0, 0, .025)
      }
    }
  }

  .avatar-container {
    margin-right: 30px;

    .avatar-wrapper {
      margin-top: 5px;
      position: relative;

      .user-avatar {
        cursor: pointer;
        width: 40px;
        height: 40px;
        border-radius: 10px;
      }

      .el-icon-caret-bottom {
        cursor: pointer;
        position: absolute;
        right: -20px;
        top: 25px;
        font-size: 12px;
      }
    }
  }
}

</style>
