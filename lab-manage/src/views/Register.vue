<template>
    <div>
        <Header></Header>
        <el-container>
            <el-main>
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="用户名" prop="userName">
                        <el-input v-model="ruleForm.userName"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input type="password" v-model="ruleForm.password"></el-input>
                    </el-form-item>
                    <el-form-item label="名字" prop="nickName">
                        <el-input v-model="ruleForm.nickName"></el-input>
                    </el-form-item>
                    <el-form-item label="老师名字" prop="tchName">
                        <el-input v-model="ruleForm.tchName"></el-input>
                    </el-form-item>
                    <el-form-item label="角色" prop="role">
                        <el-select v-model="ruleForm.role" placeholder="请选择身份">
                            <el-option label="学生" value="0"></el-option>
                            <el-option label="实验老师" value="1"></el-option>
                            <el-option label="机房老师" value="2"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
                    </el-form-item>
                </el-form>
            </el-main>
        </el-container>
    </div>
</template>

<script>
    import Header from "../components/Header";
    export default {
        name: "Register",
        components: {Header},
        data() {
            return {
                ruleForm: {
                    userName: '918106840317',
                    password: '123456',
                    role: '0',
                    nickName: '陈浩东',
                    tchName: '测试'
                },
                rules: {
                    userName: [
                        { required: true, message: '请输入用户名', trigger: 'blur' },
                        { min: 12, max: 12, message: '请输入正确的用户名！', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' },
                        { min: 6, max: 15, message: '密码为6-15位！', trigger: 'blur' }
                    ],
                    role: [
                        { required: true, message: '请选择身份', trigger: 'change' }
                    ],
                    nickName: [
                        { required: true, message: '请输入名字', trigger: 'blur' }
                    ],
                    tchName: [
                        { required: true, message: '请输入老师名字', trigger: 'blur' }
                    ],
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        const _this = this;
                        this.$axios.post('/register',this.ruleForm).then(res =>{
                            _this.$router.push('./');
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            toRegister(){
                this.router.push('/Register');
            },
        }
    }
</script>

<style scoped>
    .demo-ruleForm{
        margin: 100px auto;
        padding: 50px 70px 50px 0px;
        max-width: 500px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    }
    .el-header, .el-footer {
        background-color: #57247B;
        color: white;
        text-align: center;
        line-height: 60px;
    }

    .el-aside {
        background-color: #D3DCE6;
        color: #333;
        text-align: center;
        line-height: 200px;
    }

    .el-main {
        background-color: white;
        color: #333;
        text-align: center;
        line-height: 160px;
    }

    body > .el-container {
        margin-bottom: 40px;
    }

    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
        line-height: 260px;
    }

    .el-container:nth-child(7) .el-aside {
        line-height: 320px;
    }
</style>