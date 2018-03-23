/**
 * Bind methods on request
 * @param {*} req 
 * @param {*} res 
 * @param {*} next 
 */
function bindAuthenticated(req, res, next) {
    if(req.session && req.session.user){
        req.isAuthenticated = true
        req.user = req.session.user
    }    
    next();
}
/*
 *  Generic require login routing middleware
 */
function isLogin(req, res, next) {
    if (req.isAuthenticated) return next()
    if (req.method === 'GET' && req.accepts('text/html')) {
        const session = req.session
        session.redirectTo = req.originalUrl
        return res.redirect('/auth/signin')
    }
    return res.status(401).json({ code: '401', msg: 'Not Authenticated' }) // for ajax request
}
/**
 * Check if the login user is customer, must be used after requiresLogin
 */
function isCustomer(req, res, next) {
    if (req.user.authorities && req.user.authorities.includes('ROLE_CUSTOMER')) return next()
    return res.status(403).json({ code: '403', msg: 'Not Authorized' })
}

/**
 * Check if the login user is admin, must be used after requiresLogin
 */
function isAdmin(req, res, next) {
    if (req.user.authorities && req.user.authorities.includes('ROLE_ADMIN')) return next()
    return res.status(403).json({ code: '403', msg: 'Not Authorized' })
}


const isAdminLogin = [isLogin, isAdmin]
const isCustomerLogin = [isLogin, isCustomer]
module.exports = {bindAuthenticated, isLogin, isAdminLogin, isCustomerLogin }
