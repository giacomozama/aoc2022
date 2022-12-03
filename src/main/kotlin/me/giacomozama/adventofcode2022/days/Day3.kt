package me.giacomozama.adventofcode2022.days

class Day3 : Day() {

    private val input: List<String> = listOf(
        "WVHGHwddqSsNjsjwqVvdwZRCbcJcZTCcsZbLcJJsCZ",
        "hngprFFhFDFhrDpzzQDhtnBJJRJZbZvTcvbfRCJfBRcBJl",
        "DmptngtFwvvMmwmm",
        "HFddrJnLdqtHBMQBmmVm",
        "gbvNsbhsvQtmZTbQPT",
        "vDshDlczcDhcssscwzQwslLJrSJLpqrrzpnCrSfLSnqq",
        "pDGQDSpFDGzFDQSJqzDhjhQMTjTrwTstbTBTjTtLtbTMBT",
        "zgzVNHHgMwMLbLNB",
        "WRWPgdHCZccggJmJGzJmzGhGCD",
        "sVJNlhldShpdpnnwVnwCwtwggt",
        "WLFFcHWQLPPZQCgtnCgmbtbHwt",
        "MPLWzRMMcGgRrWNDpSSSfDflMlTd",
        "BBMZJcgBRjCZZzBpSvHQvbLvvHCQLQ",
        "VlVTFwDTVGnfWSQPtsDPbvrpDS",
        "wWdqhWlwGVfGwlfTVqFWfWWjzZZBJmMZMNdzZJMpjzNjgJ",
        "FBWFphQBmDmpmMSpDWVcVcvsPcRbrjPMcMsr",
        "HHtdnHnwNCHCTJRTPTzrbvVbcVRr",
        "lHqHwlnlqnGCNGGmWDvvZfpZvG",
        "mfVtmPtsccMmHcHCFfmhVmnpgZLbWPZqWnpqZbZWpgPW",
        "zzvwBrzdQDvpZJfQJZJpLf",
        "BrTBwRdNcfNmfStc",
        "sTlhFLfZTTLcfsLlLDZflvQvRNqRJFNvRMRNvQQRBQ",
        "CWcgwStWwCWWwvgNQvJBvQMQRB",
        "wptGzbzGWVGSCVVlVlLDcVVsfhLTlf",
        "HVnMVGwLLbsGnVsLnwLSBggMhjmgmgmhtmqhtgMhMj",
        "zrZzJRZfzZfrPCrFcWccPdTdHHlvdmlgTghCtmtTgq",
        "NFfcZWWzZrrHLBpBBGVGNG",
        "HqFhhCBCBLmwwCqJCHFvvFdcprrrSSrjRFRjpgrggb",
        "VGzWtQzGGQPVtlVNslVWsPdRpmcRrjpSzcrcbdSmSnSg",
        "WPPGllQMPGmTLvLJBCwM",
        "PvDWRSmTVvSvRhbZRpRpbjjjzM",
        "GBFGHLglHrrrLgGrttbMjpbcpcZJBsBp",
        "lrHgrrndgdNnlHGFQPMMmWPTvvWSCDQn",
        "mmhQShhmhQfzNfTTlShbHJrRtltltJJtHlRLLZ",
        "WscggNqwPWjcGcWWcpNcRJHHprZvZHrvtttZJpJr",
        "jGjgcMGCwPNsGDCcszBfhhQQQDnFnTVVBV",
        "mcGjrwzQcrZtQzZQDZcPssvPVVCPCVLwswwPBC",
        "NJbqHddNSgdPWvvsVHVLPs",
        "NqglNSlJFNSbSNdldNlNdNbTRFDrvRmQrQGtmDrvttQmmtDj",
        "zzcBPnHBjgHjWJvbJQTvScbwcQ",
        "qdspVCFqVqfFqLFCqtpTwtpTbSTbJpwBST",
        "FRLFRCNNqMfdWNmZPBPZrHmm",
        "VmtRRJmtrDrwhRcvPspltvgqtqsd",
        "WGQBZzMMBGBGbZTTWWCMNSgggqnPlsfbqndndccglffg",
        "CWQQZMFWdzMQdJJwJVFrwmmmRw",
        "rZsFfGfNhznzsjhzZfVjGVvVdvSTSJHSDDtcmmmttC",
        "wWpRBWlbWMWlQDvCcRSvJRSStm",
        "LPlwWqbgwqjjcFshNf",
        "lsppsGphmPrRQnvHdRpd",
        "qBgjLqMjgjTLPnzHPrPRLnzv",
        "gSMfNjNtttVbqBbtTSStjTqlhmlZDsDsbWZWFFFsGhlWPm",
        "sPDPDzrGzBsGRsbwrjtSVvthVfQtQw",
        "ClpgFZgNqMWCgqCpMNZqNWmNdtSwtljtVHQhtwfvdHtSdhSj",
        "FpNCJpNcpfCpgNWPGBLcbTGTzTzPnG",
        "mssNLCZqSqmNCHmrqHChJTjTjnRRnnqVnTTGngGTRn",
        "dbwptFwQbvdtcvpZDcDddgzGPjTGgpPTRpzzzgRzTn",
        "BwZdtZldDbrSsNrsrSHl",
        "MLnFWMRWpnpnLnLCmPGTqQsFzBttTQ",
        "SwNlDHNcddglSDBjrqmqGQqqmGtGGwszPP",
        "vdSlNcrvvvnBMbBR",
        "psZPRmTpRpgrlrDRBFgV",
        "jvCqNhwnjhGNqCMqVgFHWtgHBrtwHFrJ",
        "cGvbNcjvvhhjcvbQGcZdZSQpzdpmpPVpdZpd",
        "drTHDdlHzllZDTzTQRQLsPPSsBbSjQdL",
        "MfVVWmNvMnqNmVVpMMgfgMmvBFFfRRLQPPsPfsFLFCFRSFjR",
        "whMnNVnqWmlllHswJTZT",
        "ZSQTTLLlTsbmmDZlmNQSNFfPwHwqCjCCfjwFPwfwLr",
        "MctMJMBVttnhJcBBVctwRHjHRJwJwjFfqPfRwj",
        "vzqgqhBVzzTlZmmTlN",
        "WgvlHJFvljvdBmzcvcwpmchc",
        "TQqZsTZttLZbRZsLLMzzppBmNShCmBNTcNCN",
        "LPMZsMZLMQVgglFPhFHlFl",
        "qsBCPVPqVbwfnMQNmZJnqJgR",
        "hHdrvvLWtvtjWQnZJTMrmpTZgN",
        "DShSShLZdFGPGDPGsPsG",
        "qRBddRzFFqFqHnNnPSnnmmSpgpJm",
        "ssZDQMvvMwppNJWRDRpW",
        "MMvwlsRMcQBjcLqLBBqc",
        "ZGHpwFGvwpHrvfFTMtDfccMjntMntc",
        "RgSCLRLJRSRSQQqJmTDMPMTtsJjnclBjtj",
        "LVmmSSddLCwVHDbzDzZr",
        "psgWdsBjnnJjbZWQDDLNrDcrLVQjLM",
        "zPSCCHqCfqfmWNMcrVSLRM",
        "TPHzWPFTGztqTGgdJdsssvZgwb",
        "gcFgBChcClJjNCPb",
        "sWZdZdrSmWmSZRwSmsvPlsTtTtNMnnlvnJJv",
        "GSWrHZdGQpRrrSGmpWQmQfLfpVzDfghppzBVLBlBqg",
        "BFNqFzBNhqVwmTtsqVst",
        "dMwMwMfCMWbDtDvDssCC",
        "ldMwMSHHMMWJpRpPLLpBzPZjgnZPhN",
        "WczRJhcWggVBdzPPLnCjdvjm",
        "lSpSTpTSsCCmmntNdp",
        "wSFDCTwsGDqQqQVWWcJw",
        "RqPqhDGBhRDrrhBFmPmbgssZbwbgCbwsmZsQ",
        "nCtjMppjfTpjJJfVZwtzZtllLZwLss",
        "MHfpMWdHpSCSfnSTJWhDDFDFBGqDGvvDBDFd",
        "MCCGMCSHVGNTspVWQznddndg",
        "rttLtvRbrhLZrbcQdJnnQdfddsrggf",
        "BbRqltRtHsNNllNC",
        "ncFpcsLLdFmWlRmnllTR",
        "bMMVzVqMzjNVDblmRTPGlSmmPlqG",
        "gNDDJMVZNCbNJNDNQCbZCbscvBsdBvrRHfcpdQpfFFff",
        "VnWFbZvFbHWhFjZWVJZJLZFWTttpMCspQTTzQCHpgQMgztzT",
        "dGcfdNdGrlRlBDGNSllfBMspgzmTgtQQgztMtzpmcT",
        "lBNdqRsBRdfPNrLPFVVPVJPZZvhj",
        "TLWgggJzwjgWgjgGnnmQnzQfNNNQsm",
        "SpPbBlPBMlvFZpbbBmQGsmCJmCstsdNGBQ",
        "MhSHhZPrPbvSFrJPpPMSbMcLjjqTLHDRTDDTTLDLTqgH",
        "fprRRbbznFbcQVPDdQPdFV",
        "LTvmsLmcsHmvDvSZDZVVSS",
        "jWtmLccssJTLjHmLWWJwnwlBfwnBbllpCBnffbBr",
        "plPBWzbnFLPPtGqMMwlMGwmS",
        "ZQjDHjrQjdjVFwdMvCSfmwMqdt",
        "DDhhrRDjQghHJjhWBbgbTccbsTzpWF",
        "vgCbbwsTbWWWgwBWDGGDqtPGtMgGlFMH",
        "znrznJNhLSLphRRRDlFPMmpFPjjHtMFF",
        "llNcSQVSNcRbvCwwWcTdwZ",
        "qpnJbnRRnJhRFhFHRgQSzHlSRHCCCg",
        "fMBttBvsBjffvsQTtfGTWlCWsSgSmHCzZmLlHgzZ",
        "ffMdjrfdwjfwwnhJPFchhqwQ",
        "NCVSTCVCQCCRVDQSJsqFPsPNspFhhsgjPh",
        "btvtWtcWnpgmFhjmmt",
        "cfnffBfcWcrMdbvMQJDDrDTDVCpCDrGD",
        "fZNhBWFSlFQFjWQTTldHgCwvTvqqdr",
        "zznVzCznmHvnwgdH",
        "PMMbCGPMDPcLbJhFhWhBhRScQZBQ",
        "WQMrDWGHbSWHMNrTQRhghmgPZccmqDLwPqPg",
        "svCzfpdzzdsnslCsnPZcHZPlJcqZgmqPPc",
        "nntVpdpVsfjCHzvnsCzRTBrtWGbNNQSMbTNSRr",
        "SnpDQdBqGpDSBMfQGcMQBDJPNstvJcWNsPJCtJtNRWPC",
        "VrVHrhTHlPHTvvNtbhNRNswC",
        "TzlFHHmrVlgTlTGSzGqpdMGBPQBS",
        "zrCDnrDVCnCgnrHgGDnVVCZsNttQZmjtsmbMqGqsjbqj",
        "TlRRWPSwwFwbSwTTTpNQQqNjqZZlmMMQQt",
        "wvbwbRTLWdFFwvRBTbvTTRzrnznnJrDDCzBczBfHCJnz",
        "SvTdmLNNNdvTBmvmLvSvDpgczzjfgjggpcjcNPzD",
        "VJHQsJVlHpjjpzsjzP",
        "VRlJbJQrVbVHJJPMhBdnBRCSLZZZnvnLvv",
        "tMGcpGtMtLtsCGspLzNCBBmwCzQRzBBRWQ",
        "hdlHFllDdZgDbDDlDHTWWTnzBBBvzmNHzwRz",
        "FSddDlFRqDFqFSdPVqdhcfGMsVtVfLjrfGfjtMcs",
        "RGMWnBMWfCCMBHTDptJJgZStRPmSRD",
        "bqzFqjqcFLNLZZSmpSBgZZ",
        "rFrQNbNBlNcbrQlNQvvclMswTCTCnwrwHrWGsGCswn",
        "WLhJQddCQwRNCQNHczHNzMvZcZvcNc",
        "SlSpSlrpDqnbqDjlGjGGljTjMZZPPMMfVPgfHMMVgVvqfgcw",
        "SbGsDspbbnjTjBldCFmLwFCJLBmtJB",
        "TMDjMvMqMvDTzcmFCgrJCr",
        "ZZZJSZWVBHZWSSZQJhVhWnHJwczGGwGcCCFzwgmzcwFgwVzc",
        "pLHNQSnNJsMLRJds",
        "TsLZGwdsDFWHBZJFfZ",
        "mqhRvqrzJRbmzJBFfgHHgWgHrrlH",
        "JvvNhJmvtDdsNTwdLV",
        "wwnSVSmwtbstznwgbzzVMTNpTNWdlCSlSWTffWNCSN",
        "cFvccLGFGvvGHZflnNTpnZpZcB",
        "GPqGDhGGqrDhVRgbnbttPmgs",
        "rzSZJScLrcBLvjvsqMPZvjQl",
        "nnpDqgDqFTgwqHHvMHvvvTvPMM",
        "GnqCGpDqqVhccLmrmSmCRL",
        "tJSTmdfddDTDJCPmbQvQLHvqqqbrbvlP",
        "zWGsjcwwGGcVVjcGWcNjvNjQqrQtNFFQHHrF",
        "RZnRVsswRsGWcwVBZVtBRdDJgCffTgmgfnnCpfTfTM",
        "FnCrzhTrNPrMcnhMTnZZZNPwDPdbDmdDtwjdtjbmQwDt",
        "sBvWrpppvLBsLRVBfHSfbbQmbwSjStDSwSwS",
        "LVRRRJqqlHNlNTChrhMG",
        "WNsfsstMvtMvNNGPZwmZmqZPLWZcww",
        "rDCdDRCDFQjSVLcmZcDq",
        "bBBHqTgBbQlQRCQFbgqdhvshGvTJMnfTtnnThnsN",
        "VwWBTNQcVzDtrgfrtzzt",
        "LLbpShLGvlbCmLjpGSCSCpvFdrgdddcHtrtGgfqHcDHqrd",
        "pmvLmlpmjbLbpljJPBBcTBBQRZBBVJRZ",
        "cVTcVTNvvghNhvggPPgtCVSpSQmzCqZDRCmDZDZS",
        "dGJMWFsFMFWsnlzRlQzlzqpzlZzD",
        "HdLFssFMsJbnbFjqbhPgjNggcrhg",
        "LLVhQCTvRvmWlCppQfQQjPrwszNsfzNz",
        "BZSgncHgnJStJHJgntMWzGsrPqGwsfPfGPwwwZ",
        "bdBdJMBcShWCLbhWVC",
        "vjdpGNGwSNCTwwRbfnWgQMLjQWMnLQ",
        "DcmFPFtHmlcgpqWDnMbDLf",
        "FZJPtcprHtPPHplZHPZclwwGBSZSvSwCwvZzNdwvvw",
        "CdJLJCJPWPWcbtzJtqJzFrQvBhfjBBvjjvdjpFjr",
        "sBRgsZGDNSBBRGDwphrrrThpHpgHvhpQ",
        "DwDsGBDNwGmMNlMlMDSPmztJVCbVCCWqPqJLmW",
        "LSTMgDSRSMHbMDWLHSvDScwtCGqGrjGrcLftqVGtVC",
        "hzJPmlphCGrCwVrJ",
        "zhPNdNnQZBZBhZnNZSgMWMDbMHwWSDWNDH",
        "rcdvvcwvrHrMZBjHSZ",
        "sDtWblgnltsDFlgFqltCCVQTMTgSHVTfSQfSHj",
        "tDtRWFpFbWWWWNNDWsNqWvmzvhzhzGmzjjGvLwJmpc",
        "nFSSnnbhSfgLSSnVjdjfHMgfMzGzmqlNGGmTPlqqTzTNNzlT",
        "pBZsJJvccbBmlWGlNb",
        "cvsssvZwsDwrDdfFgnbDfVbgng",
        "mWRNWNCTdwdCwhCddbWWmhsZVgJQJBVBfsBsJQLQBLJb",
        "qFFlGzFtjjcqzHtFtlRfVfsZfQHVfBHQRHgf",
        "jqGjtcDnGnPzFRlzrnMdWrrCMMddNNWT",
        "MHWCjjGMcHhbhPDLphHQ",
        "nRVJrtgssdLgCppvLQbg",
        "RlVVZNVRJlsstldsBCNlczfjjSZmWTcmGmTSfmSm",
        "RTHqgTgMwgnGTRzqTHCGfdFdfhmBrJrdvbFJMhPB",
        "lNZNNNLttLWJBPBdZBFmdZ",
        "SppscpLVStclNPWtCczqnQQwHTTgCGwq",
        "hSHRCbZRSZhbRZBctnMVjwwtWtwh",
        "GrdFzQrDdJstjcWttwsF",
        "drPJLDPGPvDvzrJPQLdDHpZlwLgRmwCHLpwgSbff",
        "zMSSnCtCdSdCtdfMdHMdtVBDjhWDHBqbTVVBqhbDjr",
        "cPNhFFNRlNDlTBqjlTBG",
        "RvmvRpPNRgwgPvFwhmdCssmCzdMshMmL",
        "tttjgrpTwmCgCwgwrrlrHzbzqqFNzdJqqZnddJwNbh",
        "cQjMjPMBfcLBSjGQBndFnzNdNnhzzNGFbF",
        "sSQPLMfVPBVSfBMvVLSPfHCttDjCDRRtrVVglgpttD",
        "vdTvdpBvcTPdSSvCLrCCDLDCQGDl",
        "sRfnFgmFRMVsnqgRmqzmrrDBDwtHlLHtrLCDGL",
        "qRMVjJgRFnJfMssMsgZScPJpZbPbPPWhBZSp",
        "ZJgNJhGZglMZZFDTPSNqFSqTSb",
        "mwdvwpsjrcjBvpwFrvbHcDqbWHRWDSPWDHSR",
        "CsvpsLLjFzhlLGFZ",
        "sDNQrMrNfrlQjJRgGjbTllHG",
        "ZRhSnWFVSwBtFRBVvVgHgbzjgGTJnngmGmHC",
        "vWZLShhvZLVtSFSLqwVrQdqpcqMDddRNQMdsNP",
        "hQhSQbbwtHzShwhSQPbJRsLwRCjJmDCcvmqCcs",
        "FNdBTBTNMsRqqCjTjL",
        "GNdrdMBVFShhSLSGGL",
        "cZzcCmjjcvdzdWqgWTZgPZgZhh",
        "wSwVGSJFTffgJTNh",
        "FSVpVlBMShzbjzcpvp",
        "qqlblClRbnTvqTmRqlmnTwrdfdwFFNrngfddDBrNtr",
        "PcLcQLMVLGMzHLMchhLcjLFrrNrBfrfFNJtNgJDDBNzt",
        "sSjjGcGQscSVSMjHVMSVPSQsWmCmppZCmtCWbbWTlZTqTl",
        "qWlVJmDJHWJHVJlsdVTdhbFNNgFhwhhhFhwwZg",
        "npjnvQpStCQLvBpPnvtBtBpGSGbzbGDggGNbgwghzZNGGN",
        "jBvLtvjnrtMDmmDRTTrsWc",
        "pmwdwzJtFmmlpFsWwtstJPGgvNgCCLWCvPgNNPQCQv",
        "RfbfTRBnRGQvPNnncc",
        "ZTbPZSDSBfSBVSbbBRbbbtrFdtlFmVsswtFwzdpszw",
        "hVphQcmdcWWprWWhChFQBsfHjDTTBCHlSsTSBgSH",
        "vqBRqqzbqMZPMwSTDjJjlHDllgHZ",
        "PMnMLqtMnntQhWBccthB",
        "vqqvCSvHSSwqvqCddnvQFmNbVjbJVVmGNNVHNNlH",
        "pggrhzWgptWhZsmVlFmgNNVNbj",
        "RzpMLLhhphtzrRrSSbQTBQwSTDBwQM",
        "DSFQDlDFRddDHQHQtFlDVsVMTzrMCLSWZLZffSzLWrfCJz",
        "jjBBvpgmbppBPbMwBBBNbbZWZzzCCTzzZgzWcJccLzWz",
        "bvPwNwmpnBNhPmqpPvnwwNmtRQGQMdQDQlsGVVGhRlGFsl",
        "SfJJwDJgpGdSGJNSTwTVJDRbWWfLtCWCLtRLHWrtbWBf",
        "cQQPnFhjjQlczhqllhszhqsQRWnrbrHdHtbWrBWBbtvvHBrW",
        "qMqqqqzFFmPjmmsFjmzsmhjcDGSZTJgTdpZwZgwSZVpMTNVG",
        "czrcHMcMJtCCPnpFmH",
        "DwGGlvLljGmDRdwLdLjfhtFsssnFVpfttpptsnFPnp",
        "TlRTghTjwTDRTDlZZQgWMMrMJMSZmM",
        "BzdNzNdgNNPfgdNsdQdNvVMLLVQVMcCRCMRmvCGc",
        "zHpplwwZrZlqlWWrpZwqlHhLvqMCRDCGVmLcqGMVvCMmMD",
        "rWrjwWwHplZbwpZtHtJJbgfFTfsNnBbsfbSdTzgB",
        "jPRRppDLDGDTLLggMMjpLTGcrJWHsttJfwnWrMvrJnvnrNfJ",
        "blqbzBdzmhhbQWnsNHtJvfssfd",
        "lhFhzSzzSZVNSlVPgDPCPCGTRcGR",
        "cqWcNWffPftvsvfpqPtZsBzrbmbFddBmbcLbdDHbHz",
        "TJgljTnGgnLBTZbHdBFz",
        "JgSnJwSlgGJRwMtfPtvfwsZQZZtv",
        "hHhPbQPTwsdwdHqtgttjpNfjDt",
        "FFlCmSzRCCmlzzRGCFNvRpvjvtZNZqsRfNRg",
        "mVmsFMGFzJFBwQTMnMQndd",
        "QQVpQGcVdGmspHHLtbqfqfbt",
        "JvZTFDFzJzhFCWCZZDzWPBCJfLbnnwLqttnsHHNPwtbHLwjn",
        "DssTMWvvvGMcQGQGld",
        "sshRHZSZRbSZHhBFBMpMWpFgbbtb",
        "JfjTjmwwTPvfTNPTQlmFFFqqmFMBBqFgFt",
        "vDTvJffQTJjJvPvTNSHRzhCsShRRRDtZHz",
        "NFLsRDNNDNBDlgPPgBglQlzj",
        "HJhdZpfJzlWQjjHw",
        "ffJTppZZqTNlGnNsMG",
        "ZMrWcWwqqvPZMndGdqlnnDLnVT",
        "HpCsshCfpFfHHJDDSlSVQQGGflDQ",
        "zssNzRJFhjNHNNHpJRwbwMMzWWtZPcbBbwbG",
        "HlNHHLHsBDRpHLlsHRlJnMhfWZMRnvCCCnWhZj",
        "wtqSmQqttzSSQdPmmwZhChJjWJjPggCZCfZJ",
        "SSwtTbTQmbtdqmGTTcfqzDLHFsBLDGLNGrsBHFGrLB",
        "FFDvWznMWWMrPnPnWPgsmgQbhJRslHbwHwVVsVHjBsHb",
        "ZtSffffpdLqpSCLfCNqfLqLCjHjHbwhpBwJllHlRVQllphjj",
        "ZcNCtcGSctZScqfNGScLNcczPJFmzmDzGzWnrWFPFWDvrM",
        "DnTPspmTPsTCDQWRZzZzZRCRfCfHfh",
        "BNcqTBcFgbVchVJhVR",
        "dTwdrBrwTSPPWnnmSmsn",
        "pfbbDbHpNBFmQbpNNBSlLtlDStSdSPJLtLJR",
        "ZcszvwgVCZswFzVTRTlTlRLgRJSWJR",
        "jzZvVwFjcjjnwvzwZcjMpqMpbGQbQmhhHhmfHQmh",
        "hTbddhQCtdNmdtwtdhTBbCddRSWscczwcRSWLJzcFJzDsFsR",
        "NflgfPZPcgSLJcWD",
        "lPVNZMMMpZlZZvfrMvpbQHQhtbtqdTQHthrqhd",
        "JlWSStwhWJSRJpJvJBjTwTqcwTsDjsCTCB",
        "dqFzgFZGGQNVmTcCrjrzsBrB",
        "fdgLFQLnPdnqShRMPhlJMpWW",
        "TMPcsPDjdDhsDcDcTTTDvdvghBNFGGtmNrSrgSSBGNtNFg",
        "CVCbJqlRVVWWpRqRQZRWVWJZBtmSFGNmggGmtmmBFbrGMGMt",
        "JRqHVJVCRLZWTjMnfLTPcfLd",
        "TRTZFTTrghrZVhVWdWZpMmbzbdzBmtDpDDzmzB",
        "wcsSSsjfPfGPqQwqsQcfJJCtJGpppCBJzCbzJzCb",
        "sPjflcwljfjfvqNcTZTRhtVWrNrVLnrR",
        "rVLLsmwmCWTmsCTdwQrdTmqWDjDHjNGNPbjDBPNDNsZRDBjH",
        "cFcSvgJvfhfLnShtMJtPHRRvRbBBGBPNBHPbND",
        "hgLcgcLpJSMwzmrmzqQrmp"
    )

    // list accumulation
    // time: O(nm), space: O(1)
    override fun solveFirstPuzzle(): Int {
        var result = 0
        for (rucksack in input) {
            val mask = getCharBitmask(rucksack.substring(0, rucksack.length / 2))
            for (i in rucksack.length / 2 until rucksack.length) {
                val c = rucksack[i]
                if (1L shl (c - 'A') and mask != 0L) {
                    result += c - if (c in 'a'..'z') '`' else '&'
                    break
                }
            }
        }
        return result
    }

    // chunked list accumulation
    // time: O(nm), space: O(1)
    override fun solveSecondPuzzle(): Int {
        var result = 0
        for (i in input.indices step 3) {
            val rs1 = getCharBitmask(input[i])
            val rs2 = getCharBitmask(input[i + 1])
            val mask = rs1 and rs2
            for (c in input[i + 2]) {
                if (1L shl (c - 'A') and mask != 0L) {
                    result += c - if (c in 'a'..'z') '`' else '&'
                    break
                }
            }
        }
        return result
    }

    private fun getCharBitmask(s: String) = s.fold(0L) { acc, c -> acc or (1L shl (c.code - 65)) }
}
