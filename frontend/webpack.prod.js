const merge = require('webpack-merge');
const common = require('./webpack.common.js');

module.exports = merge(common, {
    mode: 'production',
    externals: {
        config: JSON.stringify({
            BACKEND_BASE_URL: 'http://backend:7192'
        })
    }
});