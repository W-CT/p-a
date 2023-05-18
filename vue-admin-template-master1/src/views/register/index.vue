<template>
  <div class="login-container">
    <el-form ref="registerForm" :model="registerForm" :rules="registerRules" class="login-form" auto-complete="on" label-position="left">

      <div class="title-container">
        <h3 class="title">注册</h3>
      </div>

      <el-form-item prop="userName">
        <span class="svg-container">
          <svg-icon icon-class="user" />
        </span>
        <el-input
          ref="userName"
          v-model="registerForm.userName"
          placeholder="请输入用户名"
          name="userName"
          type="text"
          tabindex="2"
          auto-complete="on"
        />
      </el-form-item>
      <span>{{ msg }}</span><!-- 简陋的用户名重复提示 -->

      <el-form-item prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password" />
        </span>
        <el-input
          :key="passwordType"
          ref="password"
          v-model="registerForm.password"
          :type="passwordType"
          placeholder="请输入密码"
          name="password"
          tabindex="3"
        />
        <span class="show-pwd" @click="showPwd">
          <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
        </span>
      </el-form-item>

      <el-form-item prop="re_password">
        <span class="svg-container">
          <svg-icon icon-class="password" />
        </span>
        <el-input
          :key="passwordType"
          ref="password"
          v-model="registerForm.re_password"
          :type="passwordType"
          placeholder="请再次输入你的密码"
          name="re_password"
          tabindex="4"
        />
        <span class="show-pwd" @click="showPwd">
          <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
        </span>
      </el-form-item>

      <el-form-item prop="checkCode">
        <span class="svg-container">
          <svg-icon icon-class="user" />
        </span>
        <el-input
          ref="checkCode"
          v-model="registerForm.checkCode"
          placeholder="请输入验证码"
          name="checkCode"
          type="text"
          tabindex="2"
          auto-complete="on"
        />
      </el-form-item>
      {{ checkCodeMsg }}
      <el-image :src="checkCodeImg"></el-image>
      <a @click="getCheckCodeImg"> 看不清？刷新验证码</a>
      <el-button :loading="false" type="primary" style="width:100%;margin-bottom:30px;" @click.native.prevent="handleRegsiter">注册</el-button>

      <div class="tips">
        <span style="margin-right:20px;">username: admin</span>
        <span> password: any</span>
        <router-link to="/login">已有账号</router-link>
      </div>

    </el-form>
  </div>
</template>

<script>
import axios from 'axios'
axios.defaults.withCredentials = true
export default {
  name: 'Register',
  data() {
    const validateUsername = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入正确用户名格式'))
      } else {
        callback()
      }
    }
    const validatePassword = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error('输入的密码不能少于6位'))
      } else {
        if (this.registerForm.re_password !== ''){
          this.$refs.registerForm.validateField("re_password")
        }
        callback()
      }
    }
    const validateRePassword = (rule, value, callback) => {
      if (value!== this.registerForm.password) {
        callback(new Error('输入的密码不一致'))
      } else {
        callback()
      }
    }
    return {
      registerForm: {
        userName: '',
        password: '',
        re_password: '',
        checkCode: ''
      },
      registerRules: {
        userName: [{ required: true, trigger: 'blur', validator: validateUsername }],
        password: [{ required: true, trigger: 'blur', validator: validatePassword }],
        re_password: [{ required: true, trigger: 'blur', validator: validateRePassword }]
      },
      passwordType: 'password',
      msg: '',
      checkCodeMsg: '',
      checkCodeImg: ''
    }
  },
  created() {
    this.getCheckCodeImg()
  },
  methods: {
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    handleRegsiter() {
      this.$refs.registerForm.validate(valid => {
        if (valid) {
          let vm = this
          this.axios({
            method: 'POST',
            url: 'http://localhost:8888/user/register',
            data: vm.registerForm
          }).then(function(resp){
            if (resp.data.code === 20000){      //后端回传状态码判断是否操作成功
              vm.$router.push("/login")
            }else if (resp.data.message === '验证码错误'){
              vm.checkCodeMsg = resp.data.message
            }else{
              vm.msg = resp.data.message
            }
          })
        } else {
          console.log('注册失败')
        }
      })
    },
    getCheckCodeImg() {
      let vm = this
      this.axios({
        method: 'POST',
        url: 'http://localhost:8888/user/checkCode',
        responseType: 'blob'
      }).then(function(resp){
        console.log(resp)
        vm.checkCodeImg = window.URL.createObjectURL(resp.data)
      })
    }
  }
}
</script>

<style lang="scss">
  /* 修复input 背景不协调 和光标变色 */
  /* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

  $bg:#283443;
  $light_gray:#fff;
  $cursor: #fff;

  @supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
    .login-container .el-input input {
      color: $cursor;
    }
  }

  /* reset element-ui css */
  .login-container {
    .el-input {
      display: inline-block;
      height: 47px;
      width: 85%;

      input {
        background: transparent;
        border: 0px;
        -webkit-appearance: none;
        border-radius: 0px;
        padding: 12px 5px 12px 15px;
        color: $light_gray;
        height: 47px;
        caret-color: $cursor;

        &:-webkit-autofill {
          box-shadow: 0 0 0px 1000px $bg inset !important;
          -webkit-text-fill-color: $cursor !important;
        }
      }
    }

    .el-form-item {
      border: 1px solid rgba(255, 255, 255, 0.1);
      background: rgba(0, 0, 0, 0.1);
      border-radius: 5px;
      color: #454545;
    }
  }
</style>

<style lang="scss" scoped>
  $bg:#2d3a4b;
  $dark_gray:#889aa4;
  $light_gray:#eee;

  .login-container {
    min-height: 100%;
    width: 100%;
    background-color: $bg;
    overflow: hidden;

    .login-form {
      position: relative;
      width: 520px;
      max-width: 100%;
      padding: 160px 35px 0;
      margin: 0 auto;
      overflow: hidden;
    }

    .tips {
      font-size: 14px;
      color: #fff;
      margin-bottom: 10px;

      span {
        &:first-of-type {
          margin-right: 16px;
        }
      }
    }

    .svg-container {
      padding: 6px 5px 6px 15px;
      color: $dark_gray;
      vertical-align: middle;
      width: 30px;
      display: inline-block;
    }

    .title-container {
      position: relative;

      .title {
        font-size: 26px;
        color: $light_gray;
        margin: 0px auto 40px auto;
        text-align: center;
        font-weight: bold;
      }
    }

    .show-pwd {
      position: absolute;
      right: 10px;
      top: 7px;
      font-size: 16px;
      color: $dark_gray;
      cursor: pointer;
      user-select: none;
    }
  }
</style>
