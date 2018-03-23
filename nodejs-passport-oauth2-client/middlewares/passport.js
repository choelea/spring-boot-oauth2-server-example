'use strict';

/*!
 * Module dependencies.
 */

const OAuth2Strategy = require('passport-oauth2').Strategy;
const passport = require('passport');
const utils = require('./utils');
const request = require('request');

const oauth2 = new OAuth2Strategy({
  authorizationURL: 'http://auth.server.com:8081/oauth/authorize',
  tokenURL: 'http://auth.server.com:8081/oauth/token',
  clientID: 'ClientId',
  clientSecret: 'secret',
  callbackURL: "http://localhost:3000/auth/aws-post-login",
  customHeaders: { Authorization : `Basic ${utils.getAuthorizationHeaderToken('ClientId', 'secret')}` }
},
function(accessToken, refreshToken, oauth2Authenticated, cb) {
  // console.log(`accessToken is: ${accessToken}`)
  // console.log(`refreshToken is ${refreshToken}`)
  console.log(`profile is ${JSON.stringify(oauth2Authenticated)}`) 
  cb(null,oauth2Authenticated)   
}
)
oauth2.userProfile = function(accessToken, done) {
  var options = {
    url: 'http://auth.server.com:8081/rest/hello/principal',
    headers: {
      authorization : `bearer ${accessToken}`
    }
  };
  request(options, (error, response, body) => {
    if (!error && response.statusCode == 200) {
      var info = JSON.parse(body);
      //console.log(info);      
      return done(null, info);
    }else{
      return done(null, false, { message: 'Failed To Authenticate' })
    }
  });
}
/**
 * Expose
 */
passport.use('oauth2', oauth2);
passport.serializeUser((user, done) => done(null, user))
passport.deserializeUser((user, done) => done(null, user))
module.exports = passport;