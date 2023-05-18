<template>
  <div class="app-container">
    <div>
      <el-button type="success" @click="addDirectory">添加分类</el-button>
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
      <el-table-column label="类别">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="类别描述">
        <template slot-scope="scope">
          <span>{{ scope.row.describes }}</span>
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
import Pagination from '@/components/Pagination'

export default {
  components: { Pagination },
  data() {
    return {
      visible: false,
      total: 0,
      list: null,
      query:{
        page:1,
        limit:5
      },
    }
  },
  created() {
    this.getList()
  },
  methods: {
    edB(id) {
      this.$router.push('/editDirectory/index/' + id)
    },
    del(id) {
      var vm = this
      this.axios({
        method: 'GET',
        url: 'http://localhost:8888/directory/delete?id=' + id
      }).then(function(resp) {
        if (resp.data == 'success') {
          vm.$message({
            type: 'success',
            message: '删除成功'
          })
          vm.getList() //刷新数据
        }
      }).catch(function() {
        vm.$message.error('删除错误')
      })
    },
    addDirectory() {
      this.$router.push('/addDirectorys/index/')
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
