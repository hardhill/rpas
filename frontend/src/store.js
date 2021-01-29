import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        url_root: "http://10.3.30.72:8098/api/",
        logins: [],
        processes: [],
        processes_name: [
            {
                id: 237,
                name: "НВП прекращение, приостановление"
            },
            {
                id: 289,
                name: "НВП назначение пенсий (федеральная)"
            },
            {
                id: 290,
                name: "НВП ЕВ СПН (федеральная)"
            },
            {
                id: 294,
                name: "НВП компенсационная выплата (федеральная)"
            },
            {
                id: 299,
                name: "НВП ежемесячная выплата Указ 175 (федеральная)"
            },
            {
                id: 300,
                name: "НВП перерасчет ЛК (федеральная)"
            },
            {
                id: 301,
                name: "НВП перерасчет ЕПГУ (федеральная)"
            }
        ],
        s_currentday: "2021-01-01",
        a_worksdates: []

    },
    mutations: {
        set_logins(state,payload){
            state.logins = payload
        },
        set_currentday(state, payload) {
            state.s_currentday = payload
        },
        set_worksdates(state, payload) {
            state.a_worksdates = payload
        }

    },
    actions: {
        act_logins({commit}){
            axios.get(this.state.url_root+"logins").then(response=>{
                commit('set_logins',response.data)
            }).catch(err=>{
                console.log(err)
            })
        },
        act_dates({commit}) {
            axios.get(this.state.url_root + "dates").then((response) => {
                commit('set_worksdates', response.data)
                if (response.data.indexOf(this.state.s_currentday) == -1)
                    commit('set_currentday', response.data[0])
            }).catch((err) => {
                console.log(err)
            })
        },
        act_worksdates({commit, dispatch}) {
            axios.get(this.state.url_root + "dates").then((response) => {
                commit('set_worksdates', response.data)
                if (response.data.indexOf(this.state.s_currentday) == -1)
                    commit('set_currentday', response.data[0])
                dispatch('act_databyprocess')
            }).catch((err) => {
                console.log(err)
            })
        },
        act_databyprocess({commit}) {
            var dts = this.state.s_currentday
            axios.get(this.state.url_root + "processesbydate?dt=" + dts).then((response) => {
                this.state.processes = response.data
            }).catch((err) => {
                console.log(err)
            })
        },

    },
    getters: {
        getLogins: state => state.logins,
        getProcessName: (state) => (idx) => {
            let f = state.processes_name.find(item => item.id == idx)
            return f.name
        }
    }
})