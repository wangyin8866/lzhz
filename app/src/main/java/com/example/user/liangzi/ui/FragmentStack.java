/**
 *
 */
package com.example.user.liangzi.ui;


import com.example.user.liangzi.ui.fragment.HomeFragment;
import com.example.user.liangzi.ui.fragment.WebFragment;

/**
 *
 *
 */
public class FragmentStack {

    // fragment constant
    public static final int FIRST_PAGE_TAB = 0x00000000;
    public static final int SECOND_PAGE_TAB = 0x10000000;
    public static final int THIRD_PAGE_TAB = 0x20000000;
    public static final int FOURTH_PAGE_TAB = 0x30000000;
    public static final int FIFTH_PAGE_TAB = 0x40000000;
    public static final int SIXTH_PAGE_TAB = 0x50000000;
    public static final int SEVENTH_PAGE_TAB = 0x60000000;
    public static final int EIGHTH_PAGE_TAB = 0x70000000;
    public static final int NINTH_PAGE_TAB = 0x80000000;
    public static final int TENTH_PAGE_TAB = 0x90000000;
    public static final int ELEVENTH_PAGE_TAB = 0xa0000000;
    public static final int TWELFTH_PAGE_TAB = 0xb0000000;
    public static final int THIRTEENTH_PAGE_TAB = 0xc0000000;
    public static final int FOURTEEN_PAGE_TAB = 0xd0000000;


    public static final int HELP_PAGE_TAB = FIRST_PAGE_TAB;//互助
    public static final int DISCOVER_PAGE_TAB = SECOND_PAGE_TAB;//发现
    public static final int NOTICE_PAGE_TAB = THIRD_PAGE_TAB;//公示
    public static final int HOME_PAGE_TAB = FOURTH_PAGE_TAB;//我的
    public static final int BANKCARD_PAGE_TAB = FOURTEEN_PAGE_TAB;//银行卡
    public static final int CERTIFICATE_PHONE_PAGE_TAB = FIFTH_PAGE_TAB;//手机认证
    public static final int CERTIFICATE_ID_PAGE_TAB = SIXTH_PAGE_TAB;//身份证认证
    public static final int CERTIFICATE_E_BUSINESS_PAGE_TAB = SEVENTH_PAGE_TAB;//
    public static final int CERTIFICATE_TAOBAO_PAGE_TAB = EIGHTH_PAGE_TAB;//淘宝认证
    public static final int CERTIFICATE_JD_PAGE_TAB = NINTH_PAGE_TAB;//京东认证
    public static final int EDIT_INFO_FRAGMENT = TENTH_PAGE_TAB;//
    public static final int READ_ALOUD_FRAGMENT = ELEVENTH_PAGE_TAB;//填写资料
    public static final int WAIT_REVIEW_STATUS = TWELFTH_PAGE_TAB;//等待审核
    public static final int LENDING_STATUS = THIRTEENTH_PAGE_TAB;//通过放款


    public static final int FIRST_FRAGMENT = 0x00000001;
    public static final int SECOND_FRAGMENT = 0x00000002;
    public static final int THIRD_FRAGMENT = 0x00000003;
    public static final int FOURTH_FRAGMENT = 0x00000004;
    public static final int FIFTH_FRAGMENT = 0x00000005;
    public static final int SIXTH_FRAGMENT = 0x00000006;
    public static final int SEVENTH_FRAGMENT = 0x00000007;
    public static final int EIGHTH_FRAGMENT = 0x00000008;
    public static final int NINTH_FRAGMENT = 0x00000009;
    public static final int TENTH_FRAGMENT = 0x0000000A;
    public static final int ELEVENTH_FRAGMENT = 0x0000000B;
    public static final int TWELFTH_FRAGMENT = 0x0000000C;
    public static final int THIRTEENTH_FRAGMENT = 0x0000000D;
    public static final int FOURTEEN_FRAGMENT = 0x0000000E;


    private BaseFragment mFirstTabFragment;
    private BaseFragment mSecondTabFragment;
    private BaseFragment mThirdTabFragment;
    private BaseFragment mFourthTabFragment;
    private BaseFragment mFifthTabFragment;
    private BaseFragment mSixthTabFragment;
    private BaseFragment mSeventhTabFragment;
    private BaseFragment mEighthTabFragment;
    private BaseFragment mNinthTabFragment;
    private BaseFragment mTenTabFragment;
    private BaseFragment mElevenTabFragment;
    private BaseFragment mTwelfthTabFragment;
    private BaseFragment mThirTeenthTabFragment;
    private BaseFragment mFourteenTabFragment;

    public FragmentStack() {
        mFirstTabFragment = createFragment(FIRST_FRAGMENT);
    }

    public BaseFragment getPreviousFragmentByTab(int tab) {
        BaseFragment fragment = null;
        if (tab == FIRST_PAGE_TAB && mFirstTabFragment != null) {
            fragment = mFirstTabFragment.getPrevious();
            mFirstTabFragment.setPrevious(null);
            if (fragment != null) {
                fragment.setNext(null);
            }
            mFirstTabFragment = fragment;
        } else if (tab == SECOND_PAGE_TAB && mSecondTabFragment != null) {
            fragment = mSecondTabFragment.getPrevious();
            mSecondTabFragment.setPrevious(null);
            if (fragment != null) {
                fragment.setNext(null);
            }
            mSecondTabFragment = fragment;
        } else if (tab == THIRD_PAGE_TAB && mThirdTabFragment != null) {
            fragment = mThirdTabFragment.getPrevious();
            mThirdTabFragment.setPrevious(null);
            if (fragment != null) {
                fragment.setNext(null);
            }
            mThirdTabFragment = fragment;
        } else if (tab == FOURTH_PAGE_TAB && mFourthTabFragment != null) {
            fragment = mFourthTabFragment.getPrevious();
            mFourthTabFragment.setPrevious(null);
            if (fragment != null) {
                fragment.setNext(null);
            }
            mFourthTabFragment = fragment;
        } else if (tab == FIFTH_PAGE_TAB && mFifthTabFragment != null) {
            fragment = mFifthTabFragment.getPrevious();
            mFifthTabFragment.setPrevious(null);
            if (fragment != null) {
                fragment.setNext(null);
            }
            mFifthTabFragment = fragment;
        } else if (tab == SIXTH_PAGE_TAB && mSixthTabFragment != null) {
            fragment = mSixthTabFragment.getPrevious();
            mSixthTabFragment.setPrevious(null);
            if (fragment != null) {
                fragment.setNext(null);
            }
            mSixthTabFragment = fragment;
        }
        return fragment;
    }

    private BaseFragment getFragmentByTab(int tab) {
        BaseFragment fragment = null;
        if (tab == FIRST_PAGE_TAB) {//认证
            if (mFirstTabFragment == null) {
                mFirstTabFragment = createFragment(FIRST_FRAGMENT);
            }
            fragment = mFirstTabFragment;
        } else if (tab == SECOND_PAGE_TAB) {
            if (mSecondTabFragment == null) {
                mSecondTabFragment = createFragment(SECOND_FRAGMENT);
            }
            fragment = mSecondTabFragment;
        } else if (tab == THIRD_PAGE_TAB) {
            if (mThirdTabFragment == null) {
                mThirdTabFragment = createFragment(THIRD_FRAGMENT);
            }
            fragment = mThirdTabFragment;
        } else if (tab == FOURTH_PAGE_TAB) {
            if (mFourthTabFragment == null) {
                mFourthTabFragment = createFragment(FOURTH_FRAGMENT);
            }
            fragment = mFourthTabFragment;
        } else if (tab == FIFTH_PAGE_TAB) {
//            if (mFifthTabFragment == null) {
                mFifthTabFragment = createFragment(FIFTH_FRAGMENT);
//            }
            fragment = mFifthTabFragment;
        } else if (tab == SIXTH_PAGE_TAB) {
//            if (mSixthTabFragment == null) {
                mSixthTabFragment = createFragment(SIXTH_FRAGMENT);
//            }
            fragment = mSixthTabFragment;
        } else if (tab == SEVENTH_PAGE_TAB) {
            if (mSeventhTabFragment == null) {
                mSeventhTabFragment = createFragment(SEVENTH_FRAGMENT);
            }
            fragment = mSeventhTabFragment;
        } else if (tab == EIGHTH_PAGE_TAB) {
//            if (mEighthTabFragment == null) {
                mEighthTabFragment = createFragment(EIGHTH_FRAGMENT);
//            }
            fragment = mEighthTabFragment;
        } else if (tab == NINTH_PAGE_TAB) {
//            if (mNinthTabFragment == null) {
                mNinthTabFragment = createFragment(NINTH_FRAGMENT);
//            }
            fragment = mNinthTabFragment;
        } else if (tab == TENTH_PAGE_TAB) {
            if (mTenTabFragment == null) {
                mTenTabFragment = createFragment(TENTH_FRAGMENT);
            }
            fragment = mTenTabFragment;
        } else if (tab == ELEVENTH_PAGE_TAB) {
            if (mElevenTabFragment == null) {
                mElevenTabFragment = createFragment(ELEVENTH_FRAGMENT);
            }
            fragment = mElevenTabFragment;
        } else if (tab == TWELFTH_PAGE_TAB) {
            if (mTwelfthTabFragment == null) {
                mTwelfthTabFragment = createFragment(TWELFTH_FRAGMENT);
            }
            fragment = mTwelfthTabFragment;
        } else if (tab == THIRTEENTH_PAGE_TAB) {
            if (mThirTeenthTabFragment == null) {
                mThirTeenthTabFragment = createFragment(THIRTEENTH_FRAGMENT);
            }
            fragment = mThirTeenthTabFragment;
        }else if(tab == FOURTEEN_PAGE_TAB) {
            if(mFourteenTabFragment == null) {
                mFourteenTabFragment = createFragment(FOURTEEN_FRAGMENT);
            }
            fragment = mFourteenTabFragment;
        }
        return fragment;
    }
    //获取当前哪个页面fragment
    public BaseFragment getFragment(int spec) {
        int tab = getTabBySpec(spec);
        int fragmentId = getFragmentBySpec(spec);
        BaseFragment fragment = null;
        if (fragmentId == 0) {
            fragment = getFragmentByTab(tab);
        } else {
            if (tab == FIRST_PAGE_TAB) {
                if (mFirstTabFragment == null) {
                    mFirstTabFragment = createFragment(FIRST_FRAGMENT);
                    if (fragmentId == FIRST_FRAGMENT) {
                        fragment = mFirstTabFragment;
                    } else {
                        fragment = createFragment(fragmentId);
                        fragment.setPrevious(mFirstTabFragment);
                        mFirstTabFragment.setNext(fragment);
                        mFirstTabFragment = fragment;
                    }
                } else {
                    mFirstTabFragment = orderFragment(mFirstTabFragment, fragmentId);
                    fragment = mFirstTabFragment;
                }
            } else if (tab == SECOND_PAGE_TAB) {
                if (mSecondTabFragment == null) {
                    mSecondTabFragment = createFragment(SECOND_FRAGMENT);
                    if (fragmentId == SECOND_FRAGMENT) {
                        fragment = mSecondTabFragment;
                    } else {
                        fragment = createFragment(fragmentId);
                        fragment.setPrevious(mSecondTabFragment);
                        mSecondTabFragment.setNext(fragment);
                        mSecondTabFragment = fragment;
                    }
                } else {
                    mSecondTabFragment = orderFragment(mSecondTabFragment, fragmentId);
                    fragment = mSecondTabFragment;
                }
            } else if (tab == THIRD_PAGE_TAB) {
                if (mThirdTabFragment == null) {
                    mThirdTabFragment = createFragment(THIRD_FRAGMENT);
                    if (fragmentId == THIRD_FRAGMENT) {
                        fragment = mThirdTabFragment;
                    } else {
                        fragment = createFragment(fragmentId);
                        fragment.setPrevious(mThirdTabFragment);
                        mThirdTabFragment.setNext(fragment);
                        mThirdTabFragment = fragment;
                    }
                } else {
                    mThirdTabFragment = orderFragment(mThirdTabFragment, fragmentId);
                    fragment = mThirdTabFragment;
                }
            } else if (tab == FOURTH_PAGE_TAB || tab == SIXTH_PAGE_TAB) {
                if (mFourthTabFragment == null) {
                    mFourthTabFragment = createFragment(FOURTH_FRAGMENT);
                    if (fragmentId == FOURTH_FRAGMENT) {
                        fragment = mThirdTabFragment;
                    } else {
                        fragment = createFragment(fragmentId);
                        fragment.setPrevious(mFourthTabFragment);
                        mFourthTabFragment.setNext(fragment);
                        fragment = mFourthTabFragment;
                    }
                } else {
                    mFourthTabFragment = orderFragment(mFourthTabFragment, fragmentId);
                    fragment = mFourthTabFragment;
                }
            } else if (tab == FIFTH_PAGE_TAB) {
                if (mFifthTabFragment == null) {
                    mFifthTabFragment = createFragment(FIFTH_FRAGMENT);
                    if (fragmentId == FIFTH_FRAGMENT) {
                        fragment = mFifthTabFragment;
                    } else {
                        fragment = createFragment(fragmentId);
                        fragment.setPrevious(mFifthTabFragment);
                        mFifthTabFragment.setNext(fragment);
                        mFifthTabFragment = fragment;
                    }
                } else {
                    mFifthTabFragment = orderFragment(mFifthTabFragment, fragmentId);
                    fragment = mFifthTabFragment;
                }
            } else if (tab == SIXTH_PAGE_TAB) {
                if (mSixthTabFragment == null) {
                    mSixthTabFragment = createFragment(SIXTH_FRAGMENT);
                    if (fragmentId == SIXTH_FRAGMENT) {
                        fragment = mSixthTabFragment;
                    } else {
                        fragment = createFragment(fragmentId);
                        fragment.setPrevious(mSixthTabFragment);
                        mSixthTabFragment.setNext(fragment);
                        mSixthTabFragment = fragment;
                    }
                } else {
                    mSixthTabFragment = orderFragment(mSixthTabFragment, fragmentId);
                    fragment = mSixthTabFragment;
                }
            } else if (tab == TWELFTH_PAGE_TAB) {
                if (mTwelfthTabFragment == null) {
                    mTwelfthTabFragment = createFragment(TWELFTH_FRAGMENT);
                    if (fragmentId == TWELFTH_FRAGMENT) {
                        fragment = mTwelfthTabFragment;
                    } else {
                        fragment = createFragment(fragmentId);
                        fragment.setPrevious(mTwelfthTabFragment);
                        mTwelfthTabFragment.setNext(fragment);
                        mTwelfthTabFragment = fragment;
                    }
                }
            } else if (tab == THIRTEENTH_PAGE_TAB) {
                if (mThirTeenthTabFragment == null) {
                    mThirTeenthTabFragment = createFragment(THIRTEENTH_FRAGMENT);
                    if (fragmentId == THIRTEENTH_FRAGMENT) {
                        fragment = mThirTeenthTabFragment;
                    } else {
                        fragment = createFragment(fragmentId);
                        fragment.setPrevious(mThirTeenthTabFragment);
                        mThirTeenthTabFragment.setNext(fragment);
                        mThirTeenthTabFragment = fragment;
                    }
                }
            }
        }
        return fragment;
    }

    private BaseFragment createFragment(int fragmentId) {
        BaseFragment fragment = null;
        switch (fragmentId) {
            case FIRST_FRAGMENT:
                fragment = new WebFragment();//
                break;
            case SECOND_FRAGMENT:
                fragment = new WebFragment();//个人信息
                break;
            case THIRD_FRAGMENT:
                fragment = new WebFragment();//职业信息
                break;
            case FOURTH_FRAGMENT:
                fragment = new HomeFragment();//银行卡信息
                break;
            case FIFTH_FRAGMENT:
//                fragment = new CertificatePhoneFragment();//手机服务商认证
                break;
            case SIXTH_FRAGMENT:
//                fragment = new CertificateIDFragment();
                break;
            case SEVENTH_FRAGMENT:
//                fragment = new CertificateEBusinessFragment();
                break;
            case EIGHTH_FRAGMENT:
//                fragment = new CertificateTaobaoFragment();//淘宝认证
                break;
            case NINTH_FRAGMENT:
//                fragment = new CertificateJDFragment();//京东认证
                break;
            case TENTH_FRAGMENT:
//                fragment = new EditInfoFragment();
                break;
            case ELEVENTH_FRAGMENT:
//                fragment = new ReadAloudFragment();
                break;
            case TWELFTH_FRAGMENT:
//                fragment = new WaitReviewFragment();
                break;
            case THIRTEENTH_FRAGMENT:
//                fragment = new CreditFragment();
                break;
            case FOURTEEN_FRAGMENT:
//                fragment = new ContactsFragment();
                break;
            default:
                break;

        }
        if (fragment != null) {
            fragment.setFragmentId(fragmentId);
        }
        return fragment;
    }

    private BaseFragment orderFragment(BaseFragment baseFragment, int fragmentId) {
        if (baseFragment == null) {
            return null;
        }
        BaseFragment fragment = baseFragment;
        if (fragmentId == FIRST_FRAGMENT || fragmentId == SECOND_FRAGMENT || fragmentId == THIRD_FRAGMENT || fragmentId == FOURTH_FRAGMENT
                || fragmentId == FIFTH_FRAGMENT || fragmentId == SIXTH_FRAGMENT) {
            do {
                BaseFragment lastFragment = fragment.getPrevious();
                if (lastFragment == null) {
                    break;
                }
                fragment = fragment.getPrevious();
            } while (fragment != null);
        } else {
            do {
                int id = fragment.getFragmentId();
                if (id == fragmentId) {
                    BaseFragment lastFragment = fragment.getPrevious();
                    BaseFragment nextFragment = fragment.getNext();
                    if (nextFragment != null) {
                        if (lastFragment != null) {
                            if (nextFragment != null) {
                                lastFragment.setNext(nextFragment);
                                nextFragment.setPrevious(lastFragment);
                                nextFragment.setNext(fragment);
                                fragment.setPrevious(nextFragment);
                                fragment.setNext(null);
                            }
                        }
                    }
                    break;
                }
                fragment = fragment.getPrevious();
            } while (fragment != null);
        }
        if (fragment == null) {
            fragment = createFragment(fragmentId);
            baseFragment.setNext(fragment);
            fragment.setPrevious(baseFragment);
        }
        return fragment;
    }

    public static int getFragmentSpec(int tab, int fragment) {
        return tab | fragment;
    }

    public static int getTabBySpec(int spec) {
        return spec & 0xf0000000;
    }

    public static int getFragmentBySpec(int spec) {
        return spec & 0x0fffffff;
    }
}
