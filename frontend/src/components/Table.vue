<template>
    <div class="main">
        <div class="panel">
            <div class="panel-header">
                <AppCaption/>
                <v-icon name="times" class="menu-close" @click="GotoIndex"/>
            </div>
            <div class="seek">
                <label for="seek">Поиск:</label>
                <div class="input-group">
                    <input id="seek" type="text" class="form-control" value="" autocomplete="off" v-model="s_seektext"
                           @keydown="OnKeyPressed"/>
                </div>
                <div class="note">поиск в журнале RPA по реквизитам: логин, процесс, страхователь (снилс, ФИО) номер
                    обращения, подпроцесс, результат
                </div>
            </div>
            <div class="panel-result">
                <div class="onerec" v-for="(item,index) in logdata" :key="index">
                    <div id="r-login"><span class="lbl">логин:</span>{{item.login}}</div>
                    <div id="r-workbegin">{{item.workbegin}}</div>
                    <div id="r-workend">{{item.workend}}</div>
                    <div id="r-strahovatel"><span class="lbl">страхователь:</span>{{item.strahovatel}}</div>
                    <div id="r-typeproc"><span class="lbl">процесс:</span>{{item.typeproc}}</div>
                    <div id="r-nomerobr"><span class="lbl">номер обращения:</span>{{item.nomerobr}}</div>
                    <div id="r-subtypeproc"><span class="lbl">подтип процесса:</span>{{item.subtypeproc}}</div>
                    <div id="r-result"><span class="lbl">результат:</span>{{item.result}}</div>
                </div>
            </div>
            <div class="itog">{{itog}}</div>
        </div>
    </div>
</template>

<script>
    import AppCaption from "@/components/AppCaption";
    import axios from 'axios'

    export default {
        name: "Table",
        components: {
            AppCaption
        },
        data: function () {
            return {
                s_seektext: '',
                a_logdata: [],
                s_itog: ''
            }
        },
        computed: {
            seektext() {
                return this.s_seektext
            },
            logdata() {
                return this.a_logdata
            },
            itog() {
                return this.s_itog
            }
        }
        ,
        methods: {
            GotoIndex() {
                this.$router.push("/")
            },
            OnKeyPressed(event) {
                var code = event.charCode || event.keyCode
                if (code == 27) {
                    this.s_seektext = ''
                    this.a_logdata.length = 0
                } else if (code == 13) {
                    this.DataFind(this.seektext)
                }
            },
            DataFind(txt) {
                axios
                    .get(this.$store.state.url_root + "data?t=" + txt)
                    .then((response) => {
                        this.a_logdata = response.data;
                        this.s_itog = (this.a_logdata.length > 0) ? "Найдено: " + this.a_logdata.length : ""
                    })
                    .catch((err) => {
                        console.log(err);
                    });
            }
        },
        watch: {
            seektext: function () {
                if (this.s_seektext.length == 0) {
                    this.a_logdata.length = 0
                    this.s_itog = ""
                }
            }
        }
    }
</script>

<style scoped>
    .main {
        display: flex;
        width: 100vw;
        height: 100vh;
        background-color: #2d2d2d;
        margin: 0px;
        padding: 0px;
        overflow: hidden;
        justify-content: center;
    }

    .panel {
        width: 640px;
        background-color: #2c3e50;
    }

    .panel-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 0.5rem 1rem;
        color: #0086B3;
        background-color: #97bacf;
    }

    .menu-close {
        cursor: pointer;
        color: #004444;
    }

    .seek {
        display: flex;
        padding: 1rem;
        color: #eaf6ff;
        text-align: left;
        align-items: center;
    }

    #seek {
        margin: 0px 0.3rem;
    }

    .note {
        font-size: smaller;
        color: #7f7f7f;
    }

    .panel-result {
        color: #eaf6ff;
        padding: 1rem;
        overflow: auto;
        max-height: 480px;
    }

    .onerec {
        display: block;
        margin-bottom: 0.2rem;
        position: relative;
        height: 84px;
        width: 100%;
        border-bottom: solid 4px #ffe79e;
        /*background-color: #c1c1c1;*/
    }

    .lbl {
        color: #7f7f7f;
        margin-right: 0.7rem;
    }

    #r-login {
        position: absolute;
        top: .1rem;
        left: .4rem;
        color: #eaf6ff;
    }

    #r-strahovatel {
        position: absolute;
        top: 1.2rem;
        left: .4rem;
    }

    #r-workbegin {
        position: absolute;
        top: .1rem;
        left: 10rem;
        color: #b8b9df;
    }

    #r-workend {
        position: absolute;
        top: .1rem;
        left: 22rem;
        color: #b8b9df;
    }

    #r-typeproc {
        position: absolute;
        top: 2.3rem;
        left: .4rem;
        color: #eaf6ff;
    }

    #r-subtypeproc {
        position: absolute;
        top: 2.3rem;
        left: 10rem;
        color: #eaf6ff;
    }

    #r-nomerobr {
        position: absolute;
        top: 3.4rem;
        left: .4rem;
        color: #eaf6ff;
    }

    #r-result {
        position: absolute;
        top: 3.4rem;
        left: 22rem;
        color: #b8ffaa;
    }

    .itog {
        color: #7f7f7f;
        margin-top: 1rem;
    }
</style>