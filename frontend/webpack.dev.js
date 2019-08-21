const merge = require('webpack-merge');
const common = require('./webpack.common.js');

module.exports = merge(common, {
    mode: 'development',
    devtool: 'inline-source-map',
    devServer: {
        historyApiFallback: true
    },
    externals: {
        config: JSON.stringify({
            BACKEND_BASE_URL: 'http://localhost:8080'
        })
    }
});