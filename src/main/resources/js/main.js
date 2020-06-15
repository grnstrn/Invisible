import Vue from 'vue'
import Vuetify from 'vuetify'
import '@babel/polyfill'
import 'js/api/resource'
import router from 'js/router/router'
import App from 'js/pages/App.vue'
import store from "js/store/store"
import { connect } from './util/ws'
import 'vuetify/dist/vuetify.min.css'
import * as Sentry from '@sentry/browser';
import { Vue as VueIntegration } from '@sentry/integrations';

Sentry.init({
    dsn: 'https://b64fe6dc3b2f466994c1e258129367ab@o402308.ingest.sentry.io/5277018',
    integrations: [new VueIntegration({Vue, attachProps: true})],
});

Sentry.configureScope(scope =>
    scope.setUser({
        id: profile && profile.id,
        username: profile && profile.name
    })
)

if (profile) {
    connect()
}

Vue.use(Vuetify)


new Vue({
    el: '#app',
    store,
    router,
    render: a => a(App),
    vuetify: new Vuetify({}),
})