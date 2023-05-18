<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form ref="form" :model="selectBook" label-width="120px" class="filter-item">
        <el-form-item class="filter-item">
          <el-input style="width: 200px;" v-model="selectBook.name"/>
        </el-form-item>
        <el-select style="width: 90px" v-model="selectBook.directory.id" class="filter-item">
          <el-option label="全部" value="-1"/>
          <el-option v-for="item in directoryForm"
                     :key="item.id"
                     :label="item.name"
                     :value="item.id"
          />
        </el-select>
        <el-button type="primary" icon="el-icon-search" @click="getList" class="filter-item">
          查询
        </el-button>
        <el-button type="success" @click="addbooks" class="filter-item">添加书籍</el-button>
      </el-form>
    </div>
    <el-table
      :data="list"
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="序号" width="95">
        <template slot-scope="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column label="id">
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>
      <el-table-column label="书名">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="作者">
        <template slot-scope="scope">
          <span>{{ scope.row.author }}</span>
        </template>
      </el-table-column>
      <el-table-column label="出版社">
        <template slot-scope="scope">
          <span>{{ scope.row.press }}</span>
        </template>
      </el-table-column>
      <el-table-column label="编码">
        <template slot-scope="scope">
          <span>{{ scope.row.isbn }}</span>
        </template>
      </el-table-column>
      <el-table-column label="分类">
        <template slot-scope="scope">
          <span>{{ scope.row.directory.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="数量">
        <template slot-scope="scope">
          <span>{{ scope.row.numbers }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Actions" align="center" width="230" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="edB(scope.row.id)">
            编辑
          </el-button>
          <el-button size="mini" type="danger" @click="del(scope.row.id)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="query.page" :limit.sync="query.limit"
                @pagination="getList"
    />
  </div>
</template>

<script>
import { getList } from '@/api/table'
import Pagination from '@/components/Pagination'

export default {
  components: { Pagination },
  data() {
    return {
      visible: false,
      total: 0,
      list: null,
      directoryForm: {},
      query:{
        page:1,
        limit:5
      },
      selectBook: {
        id: '',
        author: '',
        isbn: '',
        name: '',
        numbers: '',
        press: '',
        directory: {
          id: '-1'
        }
      }
    }
  },
  created() {
    this.getList(),
      this.fatchDirectoryById()
  },
  methods: {
    fatchDirectoryById() {
      var vm = this
      this.axios({
          method: 'GET',
          url: 'http://localhost:8888/book/input'
        }
      ).then(function(resp) {
        console.log(resp)
        vm.directoryForm = resp.data
      })
    },
    edB(id) {
      this.$router.push('/editBook/index/' + id)
    },
    del(id) {
      var vm = this
      this.axios({
        method: 'GET',
        url: 'http://localhost:8888/book/delete?id=' + id
      }).then(function(resp) {
        if (resp.data == 'success') {
          vm.$message({
            type: 'success',
            message: '删除成功'
          })
          vm.fetchData() //刷新数据
        }
      }).catch(function() {
        vm.$message.error('删除错误')
      })
    },
    fetchData() {
      var vm = this
      this.axios({
        method: 'GET',
        url: 'http://localhost:8888/book/list'
      }).then(function(resp) {
        console.log(resp.data)
        vm.list = resp.data
      })
    },
    addbooks() {
      this.$router.push('/addbooks/index/')
    },
    getList() {
      var vm = this
      this.axios({
        method: 'POST',
        url: 'http://localhost:8888/book/pageinfo?pageNum='+vm.query.page+'&pageSize='+vm.query.limit,
        data: vm.selectBook
      }).then(function(resp) {
        console.log(resp.data)
        vm.total = resp.data.total
        vm.list = resp.data.list
      })
    }
  }
}
</script>
<style lang="scss" scoped>
.filter-container {
  padding-bottom: 10px;

  .filter-item {
    display: inline-block;
    vertical-align: middle;
    margin-bottom: 10px;
  }
}
</style>
