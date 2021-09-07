pragma solidity ^0.6.0;
pragma experimental ABIEncoderV2;

import { MetaverseNFT } from "./MetaverseNFT.sol";
import { MetaverseNFTData } from "./MetaverseNFTData.sol";


/**
 * @title - MetaverseNFTTradable contract
 * @notice - This contract has role that put on sale of metaverseNFTs
 */
contract MetaverseNFTTradable {
    event TradeStatusChange(uint256 ad, bytes32 status);

    MetaverseNFT public metaverseNFT;
    MetaverseNFTData public metaverseNFTData;

    struct Trade {
        address seller;
        uint256 metaverseId;  /// MetaverseNFT's token ID
        uint256 metaversePrice;
        bool isValid;
        bytes32 status;   /// Open, Executed, Cancelled
    }
    mapping(uint256 => Trade) public trades;  /// [Key]: MetaverseNFT's token ID

    uint256 tradeCounter;

    constructor(MetaverseNFTData _metaverseNFTData) public {
        metaverseNFTData = _metaverseNFTData;
        tradeCounter = 0;
    }

    /**
     * @notice - This method is only executed when a seller create a new MetaverseNFT
     * @dev Opens a new trade. Puts _metaverseId in escrow.
     * @param _metaverseId The id for the metaverseId to trade.
     * @param _metaversePrice The amount of currency for which to trade the metaverseId.
     */
    function openTradeMetaverseNFT(MetaverseNFT metaverseNFT, uint256 _metaverseId, uint256 _metaversePrice) public {

        Trade storage trade = trades[_metaverseId];
        //if not new,then Opentade,or create new trade;
        if (trade.isValid == true) {
            //new trade
            openTrade(metaverseNFT, _metaverseId,_metaversePrice);
            return;
        }

        metaverseNFT.transferFrom(msg.sender, address(this), _metaverseId);
        metaverseNFTData.updateStatus(metaverseNFT, "Open");
        //tradeCounter += 1;    /// [Note]: New. Trade count is started from "1". This is to align metaverseId
        /*trades[tradeCounter] = Trade({
            seller: msg.sender,
            metaverseId: _metaverseId,
            metaversePrice: _metaversePrice,
            isValid : true,
            status: "Open"
        });*/
        trades[_metaverseId] = Trade({
            seller: msg.sender,
            metaverseId: _metaverseId,
            metaversePrice: _metaversePrice,
            isValid : true,
            status: "Open"
        });
        //tradeCounter += 1;  /// [Note]: Original
        //emit TradeStatusChange(tradeCounter - 1, "Open");
        emit TradeStatusChange(_metaverseId,"Open");
    }

    /**
     * @dev Opens a trade by the seller.
     */
    function openTrade(MetaverseNFT metaverseNFT, uint256 _metaverseId,uint256 _metaversePrice) public {
        metaverseNFTData.updateStatus(metaverseNFT, "Open");
        metaverseNFTData.updatePrice(metaverseNFT,_metaversePrice);
        Trade storage trade = trades[_metaverseId];
        require(
            msg.sender == trade.seller,
            "Trade can be open only by seller."
        );
        metaverseNFT.transferFrom(msg.sender, address(this), trade.metaverseId);
        trades[_metaverseId].status = "Open";
        trades[_metaverseId].metaversePrice = _metaversePrice; 

        emit TradeStatusChange(_metaverseId, "Open");
    }

    /**
     * @dev Cancels a trade by the seller.
     */
    function cancelTrade(MetaverseNFT metaverseNFT, uint256 _metaverseId) public {
        metaverseNFTData.updateStatus(metaverseNFT, "Cancelled");
        
        Trade storage trade = trades[_metaverseId];
        require(
            msg.sender == trade.seller,
            "Trade can be cancelled only by seller."
        );
        require(trade.status == "Open", "Trade is not Open.");
        metaverseNFT.transferFrom(address(this), trade.seller, trade.metaverseId);
        trades[_metaverseId].status = "Cancelled";
        emit TradeStatusChange(_metaverseId, "Cancelled");
    }

    /**
     * @dev Executes a trade. Must have approved this contract to transfer the amount of currency specified to the seller. Transfers ownership of the metaverseId to the filler.
     */
    function transferOwnershipOfMetaverseNFT(MetaverseNFT _metaverseNFT, uint256 _metaverseId, address _buyer) public {
        MetaverseNFT metaverseNFT = _metaverseNFT;

        Trade memory trade = getTrade(_metaverseId);
        require(trade.status == "Open", "Trade is not Open.");

        _updateSeller(_metaverseNFT, _metaverseId, _buyer);

        metaverseNFT.transferFrom(address(this), _buyer, trade.metaverseId);
        getTrade(_metaverseId).status = "Cancelled";
        emit TradeStatusChange(_metaverseId, "Cancelled");
    }

    function _updateSeller(MetaverseNFT metaverseNFT, uint256 _metaverseId, address _newSeller) internal {
        Trade storage trade = trades[_metaverseId];
        trade.seller = _newSeller;
    }


    /**
     * @dev - Returns the details for a trade.
     */
    function getTrade(uint256 _metaverseId) public view returns (Trade memory trade_) {
        Trade memory trade = trades[_metaverseId];
        return trade;
        //return (trade.seller, trade.metaverseId, trade.metaversePrice, trade.status);
    }
}
