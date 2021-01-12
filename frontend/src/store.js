import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        
        logins:[
            '000000',
            '000001'           
        ],

    },
    mutations: {
        
        set_logins(state,payload){
            state.logins = payload
        }
    },
    actions: {
        act_logins({commit}){
            axios.get("http://localhost:8098/api/logins").then(response=>{
                commit('set_logins',response.data)
            }).catch(err=>{
                console.log(err)
            })
        }
    },
    getters: {
        getLogins:state => state.logins
        
    }
})