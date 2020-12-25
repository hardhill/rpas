import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/Index'


import store from './store'

Vue.use(Router);

const router = new Router({
    mode: 'history',
    routes: [
        { path: '/', component: Index },
        ,
        // otherwise redirect to home
        { path: '*', redirect: '/' }
    ]
});



export default router;