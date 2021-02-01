import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/Index'
import Table from '@/components/Table'

Vue.use(Router);

const router = new Router({
    mode: 'history',
    routes: [
        { path: '/', component: Index },
        ,
        {path: '/table', component: Table},
        // otherwise redirect to home
        { path: '*', redirect: '/' }
    ]
});



export default router;