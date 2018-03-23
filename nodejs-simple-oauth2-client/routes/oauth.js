var express = require('express');
var router = express.Router();
var jwt = require('jsonwebtoken');
const authorization = require('../middlewares/authorization')

// Set the configuration settings
const credentials = {
    client: {
        id: 'ClientId',
        secret: 'secret'
    },
    auth: {
        tokenHost: 'http://auth.server.com:8081'
    }
};
const oauth2 = require('simple-oauth2').create(credentials);



router.get('/signin', function (req, res, next) {
    // Authorization oauth2 URI
    const authorizationUri = oauth2.authorizationCode.authorizeURL({
        redirect_uri: 'http://client.node.com:3000/auth/auth2-callback'
    });

    // Redirect example using Express (see http://expressjs.com/api.html#res.redirect)
    res.redirect(authorizationUri);

})
/* GET home page. */
router.get('/auth2-callback', function (req, res, next) {
    // Get the access token object (the authorization code is given from the previous step).
    const tokenConfig = {
        code: req.query.code,
        redirect_uri: 'http://client.node.com:3000/auth/auth2-callback'
    };

    // Callbacks
    // Save the access token
    oauth2.authorizationCode.getToken(tokenConfig, (error, result) => {
        if (error) {
            console.log(error)
            throw error
        } else {
            try {
                var decoded = jwt.decode(result.access_token);
                if (decoded) {
                    req.session.user = decoded
                    req.session.access_token = result.access_token
                    res.redirect(req.session.redirectTo ? req.session.redirectTo : '/')
                }
            } catch (error) {
                next(error)
            }
        }
        
    });
});

router.get('/me', authorization.isLogin, (req, res, next) => {
    res.json(req.user)
})

module.exports = router;
