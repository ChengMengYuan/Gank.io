package com.cmy.bigsnow.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.cmy.bigsnow.app.search.adapter.MSuggestion;

import com.cmy.bigsnow.greendao.MSuggestionDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig mSuggestionDaoConfig;

    private final MSuggestionDao mSuggestionDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        mSuggestionDaoConfig = daoConfigMap.get(MSuggestionDao.class).clone();
        mSuggestionDaoConfig.initIdentityScope(type);

        mSuggestionDao = new MSuggestionDao(mSuggestionDaoConfig, this);

        registerDao(MSuggestion.class, mSuggestionDao);
    }
    
    public void clear() {
        mSuggestionDaoConfig.clearIdentityScope();
    }

    public MSuggestionDao getMSuggestionDao() {
        return mSuggestionDao;
    }

}